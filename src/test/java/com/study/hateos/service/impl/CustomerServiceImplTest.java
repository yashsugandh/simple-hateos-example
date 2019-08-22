package com.study.hateos.service.impl;

import com.study.hateos.model.Customer;
import com.study.hateos.model.Order;
import com.study.hateos.repository.CustomerRepository;
import com.study.hateos.repository.OrderRepositroy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Test
  public void addCustomerTest(){
    Mockito.when(customerRepository.save(Mockito.any())).thenReturn(new Customer());

    customerService.addCustomer(new Customer());
  }

  @Test
  public void getCustomerByIdTest(){
    Customer customer = new Customer();
    customer.setCustomerName("dummy");
    Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(
        java.util.Optional.of(customer));
    final Customer customerById = customerService.getCustomerById(1L);

    Assert.assertEquals("dummy", customerById.getCustomerName());
  }
}
