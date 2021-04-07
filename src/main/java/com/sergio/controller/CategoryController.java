package com.sergio.controller;

import com.sergio.converter.CategoryConverter;
import com.sergio.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CategoryController {

    @Autowired
    CategoryConverter categoryConverter;

    @Autowired
    CategoryService categoryService;



    @GetMapping(value = "/categories")
    public ResponseEntity getCategories(Principal principal){
        return ResponseEntity.ok(categoryConverter.toDtoList(categoryService.getCategories()));
    }
}
