package hu.cubixwebshop.catalogservice.mapper;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubixwebshop.catalogservice.openapi.model.CategoryDto;
import hu.cubixwebshop.catalogservice.openapi.model.HistoryDataCategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryHistoryDataMapper {

    HistoryDataCategoryDto categoryHistoryDataToDto(HistoryData<Category> hd);

}
