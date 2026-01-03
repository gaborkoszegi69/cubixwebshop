package hu.cubixwebshop.catalogservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Audited
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private long id;

    @ToString.Include
    private String categoryname;

    @OneToMany(mappedBy = "category")
    private  Set<Product> products;
    //   public Long getId() {
    //     return  Long.valueOf(this.id);
    //}
}
