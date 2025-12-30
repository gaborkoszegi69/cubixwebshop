package hu.cubixwebshop.catalogservice.mapper;
import hu.cubixwebshop.catalogservice.dto.CategoryDto;
import hu.cubixwebshop.catalogservice.dto.ProductDto;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtotoProduct(ProductDto productDto);
    ProductDto productToDto(Product product);
    List<ProductDto> productsToDtos(List<Product> products);
    List<Product> dtosToProductes(List<ProductDto> ProductDtos);

    List<ProductDto> productsToDtos(Iterable<Product> findAll);

    @Mapping(target = "products", ignore = true)
    CategoryDto categoryDtoSummaryToDto(Category category);
}
