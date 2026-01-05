package hu.cubixwebshop.catalogservice.web;

import hu.cubixwebshop.catalogservice.mapper.ProductHistoryDataMapper;
import hu.cubixwebshop.catalogservice.mapper.ProductMapper;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import hu.cubixwebshop.catalogservice.model.Product;
import hu.cubixwebshop.catalogservice.openapi.api.ProductControllerApi;
import hu.cubixwebshop.catalogservice.openapi.model.HistoryDataCategoryDto;
import hu.cubixwebshop.catalogservice.openapi.model.HistoryDataProductDto;

import hu.cubixwebshop.catalogservice.openapi.model.ProductDto;
import hu.cubixwebshop.catalogservice.repository.ProductRepository;
import hu.cubixwebshop.catalogservice.service.ProductService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductControllerApi {
    private final ProductService productService;

    private final ProductMapper productMapper;
    private final ProductHistoryDataMapper productHistoryDataMapper;
    private final ProductRepository producRepository;
    private final NativeWebRequest nativeWebRequest;
    //private final QuerydslPredicateArgumentResolver prediacateResolver;
    private final PageableHandlerMethodArgumentResolver pageableResolver;
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(nativeWebRequest);
    }

    @Override
    public ResponseEntity<ProductDto> createProduct (@Valid    ProductDto productDto    )  {
        Product product = productMapper.dtotoProduct(productDto);
        return ResponseEntity.ok(productMapper.productToDto(productService.save(productMapper.dtotoProduct(productDto))));
    }
    @Override
    public ResponseEntity<ProductDto> modifyProduct(@ApiParam(value = "", required = true) @PathVariable("id") Long id,
                                                    @ApiParam(value = "", required = true )   @Valid @RequestBody ProductDto productDto){
        Product product = productMapper.dtotoProduct(productDto);
        product.setId(id);
        try {
            ProductDto savedproductDto = productMapper.productToDto(productService.update(product));

            return ResponseEntity.ok(savedproductDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<Void> modifyPriceProduce(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(name = "price", description = "", required = true, in = ParameterIn.PATH) @PathVariable("price") double price
    ){
        productService.modifyPriceProduce(id, price);
        return ResponseEntity.ok().build();
    }
    @Override
    public ResponseEntity<List<ProductDto>> findProductAll(
            @Parameter(name = "full", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "full", required = false) Boolean full,
            @Parameter(name = "page", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "size", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "size", required = false) Integer size,
            @Parameter(name = "sort", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "sort", required = false) List<String>  sort
    ) {
        boolean isFull = full == null ? false : full;

        Pageable pageable = createPageable("configPageable");

        List<Product> producties = isFull
                ? productService.findAllWithRelationships(pageable)
//				? airportRepository.findAllWithAddressAndDepartures() --> N*M sor jön vissza, ha N arrival és M departure van
                : producRepository.findAll(pageable).getContent();

        List<ProductDto> resultList = isFull
                ? productMapper.productsToDtos(producties)
                : productMapper.productsToDtos(producties);
        return ResponseEntity.ok(resultList);
    }
    private Pageable createPageable(String pageableConfigurerMethodName) {
        Method method;
        try {
            method = this.getClass().getMethod(pageableConfigurerMethodName, Pageable.class);
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        MethodParameter methodParameter = new MethodParameter(method, 0);
        ModelAndViewContainer mavContainer = null;
        WebDataBinderFactory binderFactory = null;
        Pageable pageable = pageableResolver.resolveArgument(methodParameter, mavContainer, nativeWebRequest, binderFactory);
        return pageable;
    }
    @Override
    public ResponseEntity<ProductDto> getProductById(@ApiParam(value = "", required = true) @PathVariable("id") Long id

    ){
        Product product=  productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(productMapper.productToDto(product));

    }
    @Override
    public ResponseEntity<List<HistoryDataProductDto>> getProductsHistoryById(
            @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    ) {
        List<HistoryData<Product>> products = productService.getProductHistory(id);

        List<HistoryDataProductDto> productDtosWithHistory = new ArrayList<>();

        products.forEach(hd ->{

            productDtosWithHistory.add(productHistoryDataMapper.productHistoryDataToDto(hd));

        });

        return  ResponseEntity.ok(productDtosWithHistory);

    }
}
