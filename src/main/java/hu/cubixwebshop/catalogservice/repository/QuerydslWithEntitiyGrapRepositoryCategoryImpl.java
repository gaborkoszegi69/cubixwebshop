package hu.cubixwebshop.catalogservice.repository;
import java.util.List;

import hu.cubixwebshop.catalogservice.model.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;

public class QuerydslWithEntitiyGrapRepositoryCategoryImpl
        extends SimpleJpaRepository<Category, Long>
        implements QuerydslWithEntitiyGrapRepository<Category, Long> {
    private EntityManager entityManager;
    private EntityPath<Category> path;
    private PathBuilder<Category> builder;
    private Querydsl querydsl;
    public QuerydslWithEntitiyGrapRepositoryCategoryImpl(EntityManager em) {
        super(Category.class, em);
        this.entityManager = em;
        this.path = SimpleEntityPathResolver.INSTANCE.createPath(Category.class);
        this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(em, builder);
    }
    @Override
    public List<Category> findAll(Predicate predicate, String egName, Sort sort) {
        JPAQuery query = (JPAQuery) querydsl.applySorting(sort, createQuery(predicate).select(path));

        EntityGraph<?> eg = entityManager.getEntityGraph(egName);
        query.setHint(org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD.getKey() , eg);
        return query.fetch();
    }
    private JPAQuery createQuery(Predicate predicate) {
        return querydsl.createQuery(path).where(predicate);
    }
}
