package hu.cubixwebshop.catalogservice.web;


import hu.cubixwebshop.catalogservice.openapi.api.CategoryControllerApi;
import hu.cubixwebshop.catalogservice.openapi.model.CategoryDto;
import hu.cubixwebshop.catalogservice.openapi.model.HistoryDataCategoryDto;
import hu.cubixwebshop.catalogservice.openapi.model.ProductDto;
import hu.cubixwebshop.catalogservice.mapper.CategoryHistoryDataMapper;
import hu.cubixwebshop.catalogservice.mapper.CategoryMapper;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import hu.cubixwebshop.catalogservice.repository.CategoryRepository;
import hu.cubixwebshop.catalogservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;
import com.querydsl.core.types.Predicate;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerApi {

    private final NativeWebRequest nativeWebRequest;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryHistoryDataMapper categoryHistoryDataMapper;
    private final PageableHandlerMethodArgumentResolver pageableResolver;
    private final MethodArgumentResolverHelper resolverHelper;
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(nativeWebRequest);
    }

    @Override
    public ResponseEntity<CategoryDto> createCategory(@Valid CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToCategory(categoryDto);
        return ResponseEntity.ok(categoryMapper.categoryToDto(categoryService.save(categoryMapper.dtoToCategory(categoryDto))));
    }

    @Override
    public ResponseEntity<Void> deleteCategory(Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CategoryDto> modifyCategory(Long id, @Valid CategoryDto categoryDto) {
        Category category= categoryMapper.dtoToCategory(categoryDto);
        category.setId(id);
        try {
            CategoryDto savedcategoryDto = categoryMapper.categoryToDto(categoryService.update(category));

            return ResponseEntity.ok(savedcategoryDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<CategoryDto> getCategoryById(Long id) {
        Category category=  categoryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(categoryMapper.categoryToDto(category));
    }

    @Override
    public ResponseEntity<List<HistoryDataCategoryDto>> getCategoryHistoryById(Long id  )  {
        List<HistoryData<Category>> categories = categoryService.getCategoryHistory(id);

        List<HistoryDataCategoryDto> categoryDtosWithHistory = new ArrayList<>();

        categories.forEach(hd ->{

            categoryDtosWithHistory.add(categoryHistoryDataMapper.categoryHistoryDataToDto(hd));

        });

        return  ResponseEntity.ok(categoryDtosWithHistory);
    }
    public void configPageable(@SortDefault("id") Pageable pageable) {}


 /*   @Override
    public ResponseEntity<List<CategoryDto>> findCategoryAll(@Valid Boolean full, @Valid Integer page, @Valid Integer size,
                                                   @Valid List<String> sort) {

        boolean isFull = full == null ? false : full;

        Pageable pageable = createPageable("configPageable");

        List<Category> categories = isFull
                ? categoryService.findAllWithRelationships(pageable)
//				? airportRepository.findAllWithAddressAndDepartures() --> N*M sor jön vissza, ha N arrival és M departure van
                : categoryRepository.findAll(pageable).getContent();

        List<CategoryDto> resultList = isFull
                ? categoryMapper.categoriesToDtos(categories)
                : categoryMapper.categoriesToDtos(categories);
        return ResponseEntity.ok(resultList);
    }*/
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
    public ResponseEntity<List<CategoryDto>> search(@Valid Boolean full, @Valid Integer page, @Valid Integer size,
                                                  @Valid List<String> sort) {
        boolean isFull = full == null ? false : full;

        Pageable pageable = resolverHelper.createPageable(this.getClass(), "configPageable", nativeWebRequest);

        Predicate predicate = resolverHelper.createPredicate(this.getClass(), "configurePredicate", nativeWebRequest);
        if(isFull) {
            Iterable<Category> categories = categoryService.searchCategories(
                    predicate,
                    pageable);
            return ResponseEntity.ok(categoryMapper.categoriesToDtos(categories));
             } else {
           Iterable<Category> categories = categoryRepository.findAll(predicate, pageable);
           return ResponseEntity.ok(categoryMapper.categorySummariesToDtos(categories));

        }
    }
}

