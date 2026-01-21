package hu.cubixwebshop.catalogservice.repository;

import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.QCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, Long>,
        QuerydslPredicateExecutor<Category>,
        QuerydslBinderCustomizer<QCategory>,
        QuerydslWithEntitiyGrapRepository<Category, Long>{

    @EntityGraph(attributePaths = {"productes"})
    @Query("SELECT c FROM Category c")
    public List<Category> findAllWithProductes();
    @Query("SELECT c FROM Category c")
    public List<Category>  findAllWithProductes(Pageable pageable);
    @EntityGraph(attributePaths = {"productes"})
    @Query("SELECT a FROM Category a WHERE a.id IN :ids")
    List<Category> findByIdWithArrivals(List<Long> ids);
    @EntityGraph(attributePaths = {"productes"})
    @Query("SELECT a FROM Category a WHERE a.id IN :ids")
    List<Category> findByIdWithDepartures(List<Long> ids, Sort sort);

    @EntityGraph(attributePaths = {"productes"})
    @Query("SELECT c FROM Category c WHERE c.id = :id")
    public Optional<Category> findByIdWithProductes(long id);
    @Override
    default void customize(QuerydslBindings bindings, QCategory category) {
        bindings.bind(category.categoryname).first((path, value) -> path.startsWithIgnoreCase(value));

        bindings.bind(category.products.any().productname).first((path, value) -> path.startsWithIgnoreCase(value));

    }
}
