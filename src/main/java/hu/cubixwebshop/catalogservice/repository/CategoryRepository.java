package hu.cubixwebshop.catalogservice.repository;

import hu.cubixwebshop.catalogservice.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

    @EntityGraph(attributePaths = {"productes"})
    @Query("SELECT c FROM Category c")
    public List<Category> findAllWithProductes();

    @EntityGraph(attributePaths = {"productes"})
    @Query("SELECT c FROM Category c WHERE c.id = :id")
    public Optional<Category> findByIdWithProductes(long id);

}
