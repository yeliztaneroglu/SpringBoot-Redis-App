package service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import redisDemo.model.Category;
import redisDemo.repository.CategoryRepository;
import redisDemo.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CategoryService.class})
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setCategoryName("kitap");
        category.setCategoryId("111");

        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        assertThat(categoryService.findById(category.getCategoryId()));
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        category1.setCategoryName("kitap");
        category1.setCategoryId("111");

        Category category2 = new Category();
        category2.setCategoryName("kalem");
        category2.setCategoryId("222");

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        assertThat(categoryService.getAll()).isEqualTo(categories);


    }


}
