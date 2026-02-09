package com.clone.qteenz.qteenz_web_service.seeder;

import com.clone.qteenz.qteenz_web_service.dto.category.CategoryRequestDTO;
import com.clone.qteenz.qteenz_web_service.model.Category;
import com.clone.qteenz.qteenz_web_service.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder implements CommandLineRunner {
    private CategoryService categoryService;

    public CategorySeeder(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.create(new CategoryRequestDTO("Protein"));
        categoryService.create(new CategoryRequestDTO( "Sayur"));
        categoryService.create(new CategoryRequestDTO( "Karbohidrat"));
        categoryService.create(new CategoryRequestDTO( "Lemak"));

        categoryService.deleteById(4L);
    }
}
