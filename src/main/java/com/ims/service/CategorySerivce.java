package com.ims.service;

import com.ims.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CategorySerivce {
    Category addCategory (Category category);
    Category updateCategory (Category category);
    void deleteCategory (int id);
    List<Category> getCategories();
    Category getCategoryById(int id);
}
