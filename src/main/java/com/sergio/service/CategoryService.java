package com.sergio.service;

import com.sergio.domain.Category;
import com.sergio.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.getCategories();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.getCategoryById(id);
    }
}
