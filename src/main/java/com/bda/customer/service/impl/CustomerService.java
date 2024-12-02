package com.bda.customer.service.impl;

import com.bda.customer.entity.Customer;
import com.bda.customer.repository.ICustomerRepository;
import com.bda.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.util.Optional;
@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public Optional<Customer> getCustomerByBusinessPartner(String businessPartner) {
      try {
          return customerRepository.findCustomerByBusinessPartner(businessPartner);
      }catch (Exception e){
          log.error("Error " + e.getMessage());
          return Optional.empty();
      }

    }

}
