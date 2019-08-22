package com.study.hateos.service.impl;

import com.study.hateos.model.Order;
import com.study.hateos.repository.OrderRepositroy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

  @InjectMocks
  private OrderServiceImpl orderService;

  @Mock
  private OrderRepositroy orderRepositroy;

  @Test
  public void addOrderTest() {
    Mockito.when(orderRepositroy.save(Mockito.any())).thenReturn(new Order());

    orderService.addOrder(new Order());
  }

  @Test
  public void getOrderByIdTest() {
    Order order = new Order();
    order.setDishName("pizza");
    order.setOrderId(5L);
    Mockito.when(orderRepositroy.findById(Mockito.anyLong()))
        .thenReturn(java.util.Optional.of(order));
    final Order orderById = orderService.getOrderById(5L);

    Assert.assertEquals("pizza", orderById.getDishName());
  }
}
