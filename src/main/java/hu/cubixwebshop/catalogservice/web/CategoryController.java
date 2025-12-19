package hu.cubixwebshop.catalogservice.web;


import hu.cubixwebshop.catalogservice.dto.CategoryDto;
import hu.cubixwebshop.catalogservice.mapper.CategoryMapper;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.repository.CategoryRepository;
import hu.cubixwebshop.catalogservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @PostMapping
    @ResponseBody
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToCategory(categoryDto);
        return categoryMapper.categoryToDto(categoryService.save(categoryMapper.dtoToCategory(categoryDto)));
    }

    @GetMapping
    public List<CategoryDto> findAll(@RequestParam Optional<Boolean> full) {
        return mapcategoriess(categoryService.findAll(full.orElse(false)),full);
    }
    private List<CategoryDto> mapcategoriess(List<Category> categores, Optional<Boolean> full) {
        if (full.orElse(false)) {
            return categoryMapper.categoriesToDtos(categores);
        } else {
            return categoryMapper.categoriesToDtos(categores);
        }
    }
}

