package hu.cubixwebshop.catalogservice.service;

import hu.cubixwebshop.catalogservice.aspect.LogCall;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.cubixwebshop.catalogservice.model.HistoryData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@LogCall
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductService productService;

    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Category update(Category category) {
        if(!categoryRepository.existsById(Long.valueOf(category.getId())))
            return null;
        return categoryRepository.save(category);
    }

    public List<Category> findAll(Boolean full) {
        return full ? categoryRepository.findAllWithProductes()
                : categoryRepository.findAll();
    }
    public Optional<Category> findById(long id){
        return categoryRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    @Transactional
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<HistoryData<Category>> getCategoryHistory(long id){

        List resultList = AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Category.class, false, true)
                .add(AuditEntity.property("id").eq(id))
                .getResultList()
                .stream()
                .map(o ->{
                    Object[] objArray = (Object[])o;
                    DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity)objArray[1];
                    Category category = (Category)objArray[0];
                    /*Address address = airport.getAddress();
                    if(address != null)
                        address.getCity();*/
                    //Category.getArrivals().size();
                    //airport.getDepartures().size();

                    return new HistoryData<Category>(
                            category,
                            (RevisionType)objArray[2],
                            revisionEntity.getId(),
                            revisionEntity.getRevisionDate()
                    );
                }).toList();

        return resultList;
    }
}
