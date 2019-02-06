package redisDemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redisDemo.model.Product;

import javax.transaction.Transactional;



@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, String> {

}
