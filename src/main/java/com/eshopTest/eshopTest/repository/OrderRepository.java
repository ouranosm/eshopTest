package com.eshopTest.eshopTest.repository;

import com.eshopTest.eshopTest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
