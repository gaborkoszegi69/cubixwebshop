package hu.cubixwebshop.catalogservice.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    private int id;

    @ToString.Include
    private String categoryname;

    @OneToMany(mappedBy = "category")
    private  List<Product> products;
}
