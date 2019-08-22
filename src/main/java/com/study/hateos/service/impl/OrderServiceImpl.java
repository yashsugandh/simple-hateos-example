package com.study.hateos.service.impl;

import com.study.hateos.exceptions.ResourceNotFoundException;
import com.study.hateos.model.Order;
import com.study.hateos.repository.OrderRepositroy;
import com.study.hateos.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  /**
   * The Order Repository.
   */
  private final OrderRepositroy orderRepositroy;

  public OrderServiceImpl(OrderRepositroy orderRepositroy) {
    this.orderRepositroy = orderRepositroy;
  }

  /**
   * The add order is used to add order information.
   *
   * @param order
   */
  @Override
  public void addOrder(Order order) {
    orderRepositroy.save(order);
  }

  /**
   * The getOrderById is used to get the order information using orderId.
   *
   * @param orderId
   * @return
   */
  @Override
  public Order getOrderById(Long orderId) {
    return orderRepositroy.findById(orderId).orElseThrow(
        () -> new ResourceNotFoundException("No customer present with the id : " + orderId));
  }
}
