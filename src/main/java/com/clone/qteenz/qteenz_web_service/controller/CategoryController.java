package com.clone.qteenz.qteenz_web_service.controller;

import com.clone.qteenz.qteenz_web_service.dto.ApiResponse;
import com.clone.qteenz.qteenz_web_service.dto.category.CategoryRequestDTO;
import com.clone.qteenz.qteenz_web_service.dto.category.CategoryResponseDTO;
import com.clone.qteenz.qteenz_web_service.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAll(){
        List<CategoryResponseDTO> categories = categoryService.getAll();
        ApiResponse<List<CategoryResponseDTO>> response = new ApiResponse<>(
                "success",
                200,
                "Categories retrieved succesfuly",
                categories
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> create(@RequestBody @Valid CategoryRequestDTO requestDTO){
        CategoryResponseDTO created = categoryService.create(requestDTO);

        ApiResponse<CategoryResponseDTO> response = new ApiResponse<>(
                "success",
                201,
                "Category created succesfully",
                created
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> findById(@PathVariable long id){
        CategoryResponseDTO category = categoryService.findById(id);
        ApiResponse<CategoryResponseDTO> response = new ApiResponse<>(
                "success",
                200,
                "Category with id:" + id + "retrieved sucessfuly",
                category
        );

        return  ResponseEntity.ok(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateById(@PathVariable long id, @RequestBody @Valid CategoryRequestDTO requestDTO){
        CategoryResponseDTO category = categoryService.updateById(id, requestDTO);
        ApiResponse<CategoryResponseDTO> response = new ApiResponse<>(
                "success",
                200,
                "Category with id: " + id + ",updated sucessfuly",
                category
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteData(@PathVariable long id){
        categoryService.deleteById(id);
        ApiResponse<Void> response = new ApiResponse<>(
                "success",
                200,
                "Category with id: " + id + ",deleted sucessfuly",
                null
        );

        return  ResponseEntity.ok(response);
    }





}
