package com.study.hateos.service;

import com.study.hateos.model.Order;

public interface OrderService {

  void addOrder(Order order);

  Order getOrderById(Long orderId);
}
