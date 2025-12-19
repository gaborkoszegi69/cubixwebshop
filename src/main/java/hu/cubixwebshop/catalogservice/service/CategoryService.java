package hu.cubixwebshop.catalogservice.service;

import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

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


    @Transactional
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }


}
