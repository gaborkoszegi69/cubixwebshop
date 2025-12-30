package hu.cubixwebshop.catalogservice.web;


import hu.cubixwebshop.catalogservice.dto.CategoryDto;
import hu.cubixwebshop.catalogservice.mapper.CategoryMapper;
import hu.cubixwebshop.catalogservice.model.Category;
import hu.cubixwebshop.catalogservice.model.HistoryData;
import hu.cubixwebshop.catalogservice.repository.CategoryRepository;
import hu.cubixwebshop.catalogservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
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
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToCategory(categoryDto);
        return categoryMapper.categoryToDto(categoryService.save(categoryMapper.dtoToCategory(categoryDto)));
    }

    @GetMapping
    public List<CategoryDto> findAll(@RequestParam Optional<Boolean> full) {
        return mapcategoriess(categoryService.findAll(full.orElse(false)),full);
    }
    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable long id) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return categoryMapper.categoryToDto(category);
    }
    private List<CategoryDto> mapcategoriess(List<Category> categores, Optional<Boolean> full) {
        if (full.orElse(false)) {
            return categoryMapper.categoriesToDtos(categores);
        } else {
            return categoryMapper.categoriesToDtos(categores);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> modifyAirport(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToCategory(categoryDto);
        category.setId(id);
        try {
            CategoryDto savedcategoryDto = categoryMapper.categoryToDto(categoryService.update(category));

            return ResponseEntity.ok(savedcategoryDto);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/history")
    public List<HistoryData<CategoryDto>> getHistoryById(@PathVariable long id) {
        List<HistoryData<Category>> categorys = categoryService.getCategoryHistory(id);

        List<HistoryData<CategoryDto>> categoryDtosWithHistory = new ArrayList<>();

        categorys.forEach(hd ->{
            categoryDtosWithHistory.add(new HistoryData<>(
                    categoryMapper.categoryToDto(hd.getData()),
                    hd.getRevType(),
                    hd.getRevision(),
                    hd.getDate()
            ));
        });

        return categoryDtosWithHistory;
    }
}

