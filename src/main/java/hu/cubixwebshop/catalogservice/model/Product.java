package hu.cubixwebshop.catalogservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Cacheable
@Audited
public class Product {
    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    private long id;

    @ToString.Include
    private String productname;
    @NotNull
    private double price;
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.LAZY)
    private Category category;

    private String artical_number;
}
