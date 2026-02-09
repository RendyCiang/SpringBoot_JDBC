package com.clone.qteenz.qteenz_web_service.service;

import com.clone.qteenz.qteenz_web_service.model.CategoryEntity;
import com.clone.qteenz.qteenz_web_service.model.Category;
import com.clone.qteenz.qteenz_web_service.repository.Category.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceR {
    private final CategoryRepository categoryRepository;

    public CategoryServiceR(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity create(String name) {
        CategoryEntity category = new CategoryEntity(name);
        return categoryRepository.save(category);
    }

    // READ
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // DELETE
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    // UPDATE
    public CategoryEntity update(Long id, String newName) {
        CategoryEntity category = getById(id);
        category.setName(newName);
        return categoryRepository.save(category);
    }
}
