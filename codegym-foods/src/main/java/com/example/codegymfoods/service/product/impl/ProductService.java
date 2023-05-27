package com.example.codegymfoods.service.product.impl;

import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.repository.product.IProductRepository;
import com.example.codegymfoods.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;
    @Override
    public List<Product> getALl() {
        return productRepository.findAll();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void creat(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void updateById(Product product) {
        productRepository.save(product);
    }

    @Override
    public Page<Product> getBlogPage(Pageable pageableProduct) {
        return productRepository.findAll(pageableProduct);
    }

    @Override
    public List<Product> getAllByType(int id) {
        return productRepository.findAllByType(id);
    }

    @Override
    public List<Product> getProductsById(Set<Integer> productsIds) {
        return productRepository.findAllById(productsIds);
    }

    @Override
    public Product getProductByName(String nameProductFromCartDTO) {
        return productRepository.findByName(nameProductFromCartDTO);
    }


}
