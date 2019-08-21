package com.study.hateos.service;

import com.study.hateos.model.Customer;

public interface CustomerService {

  Customer getCustomerById(Long id);

  void addCustomer(Customer customer);
}
