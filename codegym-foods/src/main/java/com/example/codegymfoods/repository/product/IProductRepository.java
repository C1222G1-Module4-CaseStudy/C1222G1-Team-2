package com.example.codegymfoods.repository.product;

import com.example.codegymfoods.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select * from product join product_type on product.product_type_id = product_type.id where product_type.id = ?",nativeQuery = true)
    List<Product> findAllByType(int id);
    @Query(value = "select * from product where name = ?",nativeQuery = true)
    Product findByName(String nameProductFromCartDTO);
}
