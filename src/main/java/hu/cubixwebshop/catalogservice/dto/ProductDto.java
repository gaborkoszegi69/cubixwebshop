package hu.cubixwebshop.catalogservice.dto;

import lombok.*;
import org.hibernate.envers.RevisionType;

import java.util.Date;

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
