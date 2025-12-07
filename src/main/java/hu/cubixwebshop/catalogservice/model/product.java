package hu.cubixwebshop.catalogservice.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Cacheable
public class product {
    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    private int id;

    @ToString.Include
    private String productname;
}
