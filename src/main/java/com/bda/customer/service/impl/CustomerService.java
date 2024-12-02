package com.bda.customer.service.impl;

import com.bda.customer.entity.Customer;
import com.bda.customer.repository.ICustomerRepository;
import com.bda.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Optional;
@Component
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public Optional<Customer> getCustomerByBusinessPartner(String businessPartner) {
      try {
          return customerRepository.findCustomerByBusinessPartner(businessPartner);
      }catch (Exception e){
          return Optional.empty();
      }

    }

}
