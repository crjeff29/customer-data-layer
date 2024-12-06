package com.bda.customer.util;

import com.bda.customer.dto.CustomerDTO;
import com.bda.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Utilities {
    public CustomerDTO setDTO(Optional<Customer> customer) {

        CustomerDTO dto = customer.map(c-> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(c.getId());
            customerDTO.setBusinessPartner(c.getBusinessPartner());
            customerDTO.setName(c.getName());
            customerDTO.setIdDocumentList(c.getIdDocumentList());
            customerDTO.setAddress(c.getAddress());
            customerDTO.setPhone(c.getPhone());
            customerDTO.setIdCustomerStatusList(c.getIdCustomerStatusList());
            customerDTO.setIdCustomerTypeList(c.getIdCustomerTypeList());
            customerDTO.setIdCustomerLevelList(c.getIdCustomerLevelList());
            customerDTO.setIdGroup(c.getIdGroup());
            customerDTO.setEmailAddress(c.getEmailAddress());
            return customerDTO;
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
        return dto;
    }
}
