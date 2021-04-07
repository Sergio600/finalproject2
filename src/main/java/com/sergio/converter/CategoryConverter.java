package com.sergio.converter;

import com.sergio.domain.Category;
import com.sergio.domain.Comment;
import com.sergio.dto.CategoryDto;
import com.sergio.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
public class CategoryConverter {


    public Category fromDto(CategoryDto dto){
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());

        return category;
    }

    public CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public List<Category> fromDtoList(List<CategoryDto> dtoList){
        return dtoList
                .stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    public List<CategoryDto> toDtoList(List<Category> list){
        return list
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
