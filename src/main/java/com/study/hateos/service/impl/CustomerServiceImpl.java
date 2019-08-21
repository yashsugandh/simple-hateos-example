package com.study.hateos.service.impl;

import com.study.hateos.exceptions.ResourceNotFoundException;
import com.study.hateos.model.Customer;
import com.study.hateos.repository.CustomerRepository;
import com.study.hateos.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

  /**
   * The Customer Repository.
   */
  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  /**
   * The getCustomerById is used to get the customer information  using customerId.
   *
   * @param id
   * @return
   */
  @Override
  public Customer getCustomerById(Long id) {
    log.debug("Inside getCustomerById method");
    return customerRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("No customer present with the id : " + id));
  }

  /**
   * The addCustomer is used to add a customer information.
   *
   * @param customer
   */
  @Override
  public void addCustomer(Customer customer) {
    log.info("Inside addCustomer method");
    customerRepository.save(customer);
  }
}
