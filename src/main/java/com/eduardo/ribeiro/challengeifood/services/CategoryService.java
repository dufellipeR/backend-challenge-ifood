package com.eduardo.ribeiro.challengeifood.services;

import com.eduardo.ribeiro.challengeifood.domain.category.Category;
import com.eduardo.ribeiro.challengeifood.domain.category.CategoryDTO;
import com.eduardo.ribeiro.challengeifood.domain.category.exceptions.CategoryNotFoundException;
import com.eduardo.ribeiro.challengeifood.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository)  {
        this.repository = categoryRepository;
    }

    public Category create(CategoryDTO categoryData) {
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public List<Category> list(){
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setTitle(categoryData.description());

        this.repository.save(category);
        return category;
    }
    public void delete(String id){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }


}
