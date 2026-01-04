package hu.cubixwebshop.catalogservice.web;

import hu.cubixwebshop.catalogservice.openapi.model.ProductDto;
import hu.cubixwebshop.catalogservice.mapper.ProductMapper;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import hu.cubixwebshop.catalogservice.model.Product;
import hu.cubixwebshop.catalogservice.repository.ProductRepository;
import hu.cubixwebshop.catalogservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository producRepository;
    @PostMapping
    @ResponseBody
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.dtotoProduct(productDto);
        return productMapper.productToDto(productService.save(productMapper.dtotoProduct(productDto)));
    }
    @PostMapping("/{id}/price/{price}")
    public void modifyPriceProduce(@PathVariable long id, @PathVariable double price) {
        productService.modifyPriceProduce(id, price);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> modifyProduce(@PathVariable int id, @RequestBody ProductDto productDto) {
        Product product = productMapper.dtotoProduct(productDto);
        product.setId(id);
        try {
            ProductDto savedproductDto = productMapper.productToDto(productService.update(product));

            return ResponseEntity.ok(savedproductDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public List<ProductDto> ProducefindAll() {
        return productMapper.productsToDtos(productService.findAll());
    }
    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable long id) {
        Product product  = productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return productMapper.productToDto(product);
    }
    @GetMapping("/{id}/history")
    public List<HistoryData<ProductDto>> getHistoryById(@PathVariable long id) {
        List<HistoryData<Product>> products = productService.getProductHistory(id);

        List<HistoryData<ProductDto>> productDtosWithHistory = new ArrayList<>();

        products.forEach(hd ->{
            productDtosWithHistory.add(new HistoryData<>(
                    productMapper.productToDto(hd.getData()),
                    hd.getRevType(),
                    hd.getRevision(),
                    hd.getDate()
            ));
        });

        return productDtosWithHistory;
    }
}
