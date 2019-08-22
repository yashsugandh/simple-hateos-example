package com.study.hateos.controller;

import com.study.hateos.model.Customer;
import com.study.hateos.model.Order;
import com.study.hateos.service.CustomerService;
import com.study.hateos.service.OrderService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class CustomerController {

  /**
   * The Customer Service.
   */
  private final CustomerService customerService;

  /**
   * The Order Service.
   */
  private final OrderService orderService;

  public CustomerController(CustomerService customerService, OrderService orderService) {
    this.customerService = customerService;
    this.orderService = orderService;
  }

  /**
   * The addCustomer route is used to add a customer.
   *
   * @param customer
   * @return
   */
  @PostMapping("/addCustomer")
  public ResponseEntity addCustomer(@RequestBody @Valid Customer customer) {
    customerService.addCustomer(customer);
    return new ResponseEntity(customer, HttpStatus.CREATED);
  }

  /**
   * The getCustomerById route is used to get the customer info from id.
   *
   * @param id
   * @return
   */
  @GetMapping("/getCustomer/{id}")
  public Resource<Customer> getCustomerById(@PathVariable Long id) {
    final Customer customerById = customerService.getCustomerById(id);
    return new Resource<>(customerById, createCustomerSelfLink(id));
  }

  /**
   * The addOrder route is used to add an order for existing customer.
   *
   * @param order
   * @param customerId
   * @return
   */
  @PostMapping("/addOrder/{customerId}")
  public ResponseEntity addOrder(@RequestBody @Valid Order order, @PathVariable Long customerId) {
    order.setCustomer(customerService.getCustomerById(customerId));
    orderService.addOrder(order);
    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  /**
   * The getOrders route is used to get all the orders of a customer.
   *
   * @param customerId
   * @return
   */
  @GetMapping("/getAllOrders/{customerId}")
  public Resources<Order> getOrders(@PathVariable Long customerId) {

    final List<Order> orders = customerService.getCustomerById(customerId).getOrders();

    orders.stream().forEach(order -> order.add(createOrderLink(order)));

    return new Resources<>(orders, createAllOrdersSelfLink(customerId));
  }

  /**
   * The createOrderLink method is used to create self link for an order.
   *
   * @param order
   * @return
   */
  private Link createOrderLink(Order order) {
    return linkTo(methodOn(CustomerController.class).getOrderById(order.getOrderId()))
        .withSelfRel();
  }

  /**
   * The createCustomerSelfLink method is used to create self link for an customer.
   *
   * @param customerId
   * @return
   */
  private Link createAllOrdersSelfLink(Long customerId) {
    return linkTo(methodOn(CustomerController.class).getOrders(customerId)).withSelfRel();
  }

  private Link createCustomerSelfLink(Long customerId) {
    return linkTo(methodOn(CustomerController.class).getCustomerById(customerId)).withSelfRel();
  }

  /**
   * The getOrderById route is used to get the order for an orderId.
   *
   * @param orderId
   * @return
   */
  @GetMapping("/getOrder/{orderId}")
  public Order getOrderById(@PathVariable Long orderId) {
    return orderService.getOrderById(orderId);
  }
}
