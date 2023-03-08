package com.eshopTest.eshopTest.repository;

import com.eshopTest.eshopTest.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    //Page<Order> findByNameContainingIgnoreCase(String name, PageRequest paging);

}
