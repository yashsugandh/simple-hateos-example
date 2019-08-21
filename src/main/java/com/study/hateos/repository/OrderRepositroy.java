package com.study.hateos.repository;

import com.study.hateos.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositroy extends JpaRepository<Order, Long> {
}
