package hu.cubixwebshop.catalogservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private int id;
    private String productname;
    private double price;
    private CategoryDto category;
}
