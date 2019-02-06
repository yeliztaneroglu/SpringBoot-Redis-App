package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import redisDemo.RedisDemoApplication;
import redisDemo.controller.RedisDemoController;
import redisDemo.model.Product;
import redisDemo.service.CategoryService;
import redisDemo.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RedisDemoApplication.class})
@WebMvcTest(value = RedisDemoController.class, secure = false)
public class RedisDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void testGetAllProducts() throws Exception {
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

        Mockito.when(productService.getAll()).thenReturn(products);

        String URI = "/products/all";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(products);
        String outputInJson = mvcResult.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);


    }

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
