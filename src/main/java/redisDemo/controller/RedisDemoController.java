package redisDemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redisDemo.model.Category;
import redisDemo.model.Product;
import redisDemo.service.CategoryService;
import redisDemo.service.ProductService;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class RedisDemoController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/categories/all", method = RequestMethod.GET, produces = "application/json")
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.PUT, produces = "application/json")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.update(category);
    }

    @RequestMapping(value = "/products/all", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getProducts() { return productService.getAll(); }

    @RequestMapping(value = "/products/byCategory/{categoryId}", method = RequestMethod.GET, produces = "application/json")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") String categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @RequestMapping(value = "/products/delete/{productid}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteProduct(@PathVariable("productid") String productId) {
        productService.delete(productId);
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.PUT, produces = "application/json")
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }
}


