package redisDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redisDemo.model.Product;
import redisDemo.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productrepository;

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productrepository.findAll().forEach(products::add);
        return products;
    }

    public void delete(String productId) {
        productrepository.delete(productId);
    }

    public Product update(Product product) {
        Product updatedProduct;
        if ((productrepository.findOne(product.getProductid())) == null) {
            updatedProduct = new Product(product.getProductid(), product.getProductname(),
                    product.getPrice(), product.getCategoryId());
        } else {
            updatedProduct = new Product();
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setProductname(product.getProductname());
            updatedProduct.setCategoryId(product.getCategoryId());
        }
        productrepository.save(updatedProduct);
        return updatedProduct;
    }

    public List<Product> getProductsByCategory(String categoryId) {
        List<Product> products = new ArrayList<>();
        productrepository.findAll().forEach(product -> {
            if (product != null) {
                if ((product.getCategoryId() != null) &&
                        (!product.getCategoryId().isEmpty()) &&
                        (product.getCategoryId().equals(categoryId))) {
                    products.add(product);
                }
            }
        });
        return products;
    }

}
