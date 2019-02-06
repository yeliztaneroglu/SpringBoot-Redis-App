package redisDemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@RedisHash("PRODUCT")
public class Product implements Serializable {

    @Id
    private String productid;
    private String productname;
    private String price;

    @ManyToOne( fetch = FetchType.LAZY)
    private String categoryId;


    public Product() { }

    public Product(String productid, String productname, String price, String categoryId) {
        this.productid = productid;
        this.productname = productname;
        this.price = price;
        this.categoryId = categoryId;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductid() {
        return productid;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productid='" + productid + '\'' +
                ", productname='" + productname + '\'' +
                ", price='" + price + '\'' +
                ", category=" + categoryId +
                '}';
    }



}
