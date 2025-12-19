package hu.cubixwebshop.catalogservice.mapper;
import hu.cubixwebshop.catalogservice.dto.ProductDto;
import hu.cubixwebshop.catalogservice.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductDto> productToDtos(List<Product> products);
    ProductDto productToDto(Product product);
    Product dtotoProduct(ProductDto productDto);
    List<Product> dtosToProductes(List<ProductDto> ProductDtos);
}
