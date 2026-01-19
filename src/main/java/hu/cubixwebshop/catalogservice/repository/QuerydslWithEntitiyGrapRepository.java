package hu.cubixwebshop.catalogservice.repository;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Predicate;
public interface QuerydslWithEntitiyGrapRepository <T, ID> {

    List<T> findAll(Predicate predicate, String egName, Sort sort);
}