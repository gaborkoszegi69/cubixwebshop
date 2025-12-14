package hu.cubixwebshop.catalogservice.dto;

import lombok.*;
import  hu.cubixwebshop.catalogservice.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private int id;

    private String categoryname;

    private List<ProductDto> productes = new ArrayList<>();

    public List<ProductDto> getProduts() {
        return productes;
    }
}
