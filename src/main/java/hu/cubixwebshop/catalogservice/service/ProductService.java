package hu.cubixwebshop.catalogservice.service;

import hu.cubixwebshop.catalogservice.aspect.LogCall;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import hu.cubixwebshop.catalogservice.model.Product;
import hu.cubixwebshop.catalogservice.repository.CategoryRepository;
import hu.cubixwebshop.catalogservice.repository.ProductRepository;
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

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@LogCall
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DelayService delayService;

    @Transactional
    public Product save(Product product) {
        Long categoryId = Long.valueOf(product.getCategory().getId());
        product.setCategory(categoryRepository.findById(categoryId).get());
        return productRepository.save(product);
    }

    @PersistenceContext
    private EntityManager em;
    @Transactional
    public Product update(Product product) {
        if(!productRepository.existsById(Long.valueOf(product.getId())))
            return null;
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return  productRepository.findAll();
    }
    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
        productRepository.deleteById(id);
    }
    @Transactional
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<HistoryData<Product>> getProductHistory(long id){

        List resultList = AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Product.class, false, true)
                .add(AuditEntity.property("id").eq(id))
                .getResultList()
                .stream()
                .map(o ->{
                    Object[] objArray = (Object[])o;
                    DefaultRevisionEntity revisionEntity = (DefaultRevisionEntity)objArray[1];
                    Product product = (Product)objArray[0];
                    /*Address address = airport.getAddress();
                    if(address != null)
                        address.getCity();*/
                    //Category.getArrivals().size();
                    //airport.getDepartures().size();

                    return new HistoryData<Product>(
                            product,
                            (RevisionType)objArray[2],
                            revisionEntity.getId(),
                            revisionEntity.getRevisionDate()
                    );
                }).toList();

        return resultList;
    }

    public void modifyPriceProduce(long id, double price) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent())
            updateProductWithDelay(productOptional.get(),price);
    }
    private void updateProductWithDelay(Product f, double price) {
        f.setPrice(delayService.getDelay(f.getId(),price));
        productRepository.save(f);
    }
}
