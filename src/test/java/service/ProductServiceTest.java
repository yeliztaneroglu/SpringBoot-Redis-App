package service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import redisDemo.model.Product;
import redisDemo.repository.ProductRepository;
import redisDemo.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductService.class})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setProductid("1");
        product.setProductname("telefon");
        product.setCategoryId("111");
        product.setPrice("500");

        Mockito.when(productRepository.findOne("1")).thenReturn(product);
        Mockito.when(productRepository.exists(product.getProductid())).thenReturn(false);
        assertFalse(productRepository.exists(product.getProductid()));

    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setProductid("1");
        product1.setProductname("telefon");
        product1.setCategoryId("111");
        product1.setPrice("500");

        Product product2 = new Product();
        product2.setProductid("2");
        product2.setProductname("televizyon");
        product2.setCategoryId("112");
        product2.setPrice("800");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(products);
        assertThat(productService.getAll()).isEqualTo(products);
    }

}
