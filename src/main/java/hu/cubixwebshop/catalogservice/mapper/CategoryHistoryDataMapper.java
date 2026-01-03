package hu.cubixwebshop.catalogservice.mapper;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubixwebshop.catalo_gservice.api.model.CategoryDto;
import hu.cubixwebshop.catalo_gservice.api.model.HistoryDataCategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryHistoryDataMapper {

    HistoryDataCategoryDto categoryHistoryDataToDto(HistoryData<Category> hd);

}
