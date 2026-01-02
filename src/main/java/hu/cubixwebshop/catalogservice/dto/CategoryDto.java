package hu.cubixwebshop.catalogservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(Views.BaseData.class)
    private int id;
    @JsonView(Views.BaseData.class)
    private String categoryname;
    @JsonView(Views.BaseData.class)
    private List<ProductDto> products;

}
