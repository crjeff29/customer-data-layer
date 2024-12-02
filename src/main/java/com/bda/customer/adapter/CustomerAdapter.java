package com.bda.customer.adapter;

import com.bda.customer.component.Constants;
import com.bda.customer.dto.CustomerDTO;
import com.bda.customer.entity.Customer;
import com.bda.customer.service.impl.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerAdapter {
    private static final String TOPIC = "customer-service-data-topic";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CustomerService customerService;

    @KafkaListener(topics = Constants.GET_CUSTOMER_KAFKA_TOPIC, groupId = "bda-group")
    public void listen(String message) throws JsonProcessingException {
        CustomerDTO dto = objectMapper.readValue(message, CustomerDTO.class);

        Optional<Customer> customer = customerService.getCustomerByBusinessPartner(dto.getBusinessPartner());

        kafkaTemplate.send(TOPIC, "customer-created");

    }

}
