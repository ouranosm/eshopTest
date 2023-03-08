package com.eshopTest.eshopTest.repository;

import com.eshopTest.eshopTest.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {



    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
