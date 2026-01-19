package hu.cubixwebshop.catalogservice.mapper;

import hu.cubixwebshop.catalogservice.openapi.model.CategoryDto;
import hu.cubixwebshop.catalogservice.openapi.model.ProductDto;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToDto(Category category);
    Category  dtoToCategory(CategoryDto categoryDto);

    List<CategoryDto> categoriesToDtos(Iterable<Category> category);
    @Named("summaryname")
    @Mapping(ignore = true, target = "products")
    CategoryDto categoryDtoSummaryToDto(Category category);
    @IterableMapping(qualifiedByName = "summaryname")
    List<CategoryDto> categorySummariesToDtos(Iterable<Category> findAll);

    @Mapping(target = "category", ignore = true)
    ProductDto productToDto(Product product);
}
