package hu.cubixwebshop.catalogservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class category {
    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    private int id;

    @ToString.Include
    private String categoryname;

}
