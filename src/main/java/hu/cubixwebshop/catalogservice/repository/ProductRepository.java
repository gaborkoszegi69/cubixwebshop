package hu.cubixwebshop.catalogservice.repository;

import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.Product;
import hu.cubixwebshop.catalogservice.model.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>,
    	 JpaSpecificationExecutor<Product>,
      QuerydslPredicateExecutor<Product>,
        QuerydslBinderCustomizer<QProduct>{
    @Override
    default void customize(QuerydslBindings bindings, QProduct product) {
        bindings.bind(product.productname).first((path, value) -> path.startsWithIgnoreCase(value));

    }

    @Query("SELECT c FROM Product  c")
    List<Product> findAllWithCategoris(Pageable pageable);
    @Query("SELECT a FROM Product a WHERE a.id IN :ids")
    List<Product> findByIdWithArrivals(List<Long> ids);
    @Query("SELECT a FROM Product a WHERE a.id IN :ids")
    List<Product> findByIdWithDepartures(List<Long> ids, Sort sort);
}
