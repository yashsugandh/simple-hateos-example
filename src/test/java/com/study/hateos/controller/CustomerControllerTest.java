package com.study.hateos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.hateos.model.Customer;
import com.study.hateos.model.Order;
import com.study.hateos.service.CustomerService;
import com.study.hateos.service.OrderService;
import com.study.hateos.util.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

  @InjectMocks
  private CustomerController customerController;

  @Mock
  private CustomerService customerService;

  @Mock
  private OrderService orderService;

  @Test
  public void addCustomerTest() {
    final ResponseEntity responseEntity = customerController.addCustomer(new Customer());
    Assert.assertEquals(201, responseEntity.getStatusCodeValue());
  }

  @Test
  public void getCustomerByIdTest() {
    Customer customer = new Customer();
    customer.setCustomerName("dummy");
    Mockito.when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(customer);
    final Resource<Customer> customerById = customerController.getCustomerById(1L);

    Assert.assertEquals("dummy", customerById.getContent().getCustomerName());
  }

  @Test
  public void addOrderTest() {
    final ResponseEntity responseEntity = customerController.addOrder(new Order(), 1L);
    Assert.assertEquals(201, responseEntity.getStatusCodeValue());
  }

  @Test
  public void getOrdersTest() throws IOException {
    final String orders = TestUtils.getFileInStringFromClasspath("get-customer-by-id.json");
    ObjectMapper objectMapper = new ObjectMapper();
    final Customer customer = objectMapper.readValue(orders, Customer.class);
    Mockito.when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(customer);
    final Resources<Order> ordersResponse = customerController.getOrders(1L);
    Assert.assertEquals(2, ordersResponse.getContent().size());
  }

  @Test
  public void getOrderById() {
    Order order = new Order();
    order.setDishName("pizza");
    order.setOrderId(5L);
    Mockito.when(orderService.getOrderById(Mockito.anyLong())).thenReturn(order);
    final Order orderById = customerController.getOrderById(5L);

    Assert.assertEquals("pizza", orderById.getDishName());
  }
}
