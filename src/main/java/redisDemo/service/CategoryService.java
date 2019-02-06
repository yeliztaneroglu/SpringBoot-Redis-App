package redisDemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redisDemo.model.Category;
import redisDemo.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryService {

    final
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(String id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public void create(Category category) {
        categoryRepository.save(category);
    }

    public Category update(Category updatedCategory) {
        Category category = new Category();
        if (updatedCategory.getCategoryId().equals("")) {
            Random generator = new Random();
            Integer i = generator.nextInt(1000000000) + 1;
            category.setCategoryId(String.valueOf(i));
        } else {
            category.setCategoryId(updatedCategory.getCategoryId());
        }

        category.setCategoryName(updatedCategory.getCategoryName());
        categoryRepository.save(category);
        return category;
    }

}
