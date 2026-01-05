package hu.cubixwebshop.catalogservice.mapper;

import hu.cubixwebshop.catalogservice.model.Product;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import hu.cubixwebshop.catalogservice.openapi.model.HistoryDataProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubixwebshop.catalogservice.openapi.model.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductHistoryDataMapper {
    HistoryDataProductDto productHistoryDataToDto(HistoryData<Product> hd);
}
