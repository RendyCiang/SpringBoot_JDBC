package com.clone.qteenz.qteenz_web_service.service;


import com.clone.qteenz.qteenz_web_service.dao.CategoryDao;
import com.clone.qteenz.qteenz_web_service.dto.category.CategoryRequestDTO;
import com.clone.qteenz.qteenz_web_service.dto.category.CategoryResponseDTO;
import com.clone.qteenz.qteenz_web_service.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

//    public void createData(Category category){
//        categoryDao.insert(category);
//    }

    public CategoryResponseDTO create(CategoryRequestDTO requestDTO){
        return categoryDao.insert(requestDTO);
    }

    public List<CategoryResponseDTO> getAll(){
        return categoryDao.getAll();
    }

    public CategoryResponseDTO findById(long id){
        return categoryDao.findById(id);
    }

    public CategoryResponseDTO updateById(long id, CategoryRequestDTO requestDTO){
        return categoryDao.updateById(id, requestDTO);
    }

    public void deleteById(Long id){
        categoryDao.deleteById(id);
    }

}
