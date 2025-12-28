package hu.cubixwebshop.catalogservice.mapper;

import hu.cubixwebshop.catalogservice.dto.CategoryDto;
import hu.cubixwebshop.catalogservice.model.Category;
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

    List<CategoryDto> categoriesToDtos(List<Category> category);

}
