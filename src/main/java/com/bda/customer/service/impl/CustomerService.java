package com.bda.customer.service.impl;

import com.bda.customer.entity.Customer;
import com.bda.customer.exception.CustomerServiceException;
import com.bda.customer.repository.ICustomerRepository;
import com.bda.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerByBusinessPartner(String businessPartner) {
        try {
            Customer customer = customerRepository.findCustomerByBusinessPartner(businessPartner);
            if (customer == null) {
                log.warn("Customer not found for business partner: {}", businessPartner);
            }
            return customer;
        } catch (CustomerServiceException e) {
            log.error("Error retrieving customer: {}", e.getMessage(), e);
            throw new CustomerServiceException("Unexpected error occurred while retrieving customer", e);
        }

    }

}
