package com.ims.service.impl;

import com.ims.entity.Category;
import com.ims.repository.CategoryRepository;
import com.ims.service.CategorySerivce;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategorySerivce {
    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository repo){
        this.categoryRepository=repo;
    }
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
