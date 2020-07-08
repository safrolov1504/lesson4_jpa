package com.geekbrains.hw4.services;

import com.geekbrains.hw4.model.Product;
import com.geekbrains.hw4.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServer {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(Specification<Product> spec, Integer page){
        return productRepository.findAll(spec,PageRequest.of(page-1,12));
    }

    public Product saveOrUpdate(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
