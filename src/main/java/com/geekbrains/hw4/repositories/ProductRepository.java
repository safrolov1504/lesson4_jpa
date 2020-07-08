package com.geekbrains.hw4.repositories;

import com.geekbrains.hw4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
//    List<Product> findAllByPriceGreaterThan(double minPrice, PageRequest of);
//
//    List<Product> findAllByPriceLessThan(double maxPrice, PageRequest of);
//
//    List<Product> findAllByPriceGreaterThanAndLessThan(double minPrice, double maxPrice, PageRequest of);

}
