package com.bda.customer.service;

import com.bda.customer.entity.Customer;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Optional;

public interface ICustomerService {
    Customer getCustomerByBusinessPartner(String businessPartner);
}
