package com.bda.customer.service;

import com.bda.customer.dto.CustomerDTO;
import com.bda.customer.entity.Customer;
import com.bda.customer.service.impl.CustomerService;
import com.bda.customer.util.Utilities;
import com.customer.grpc.BusinessPartner;
import com.customer.grpc.CustomerAccessServiceGrpc;
import com.customer.grpc.CustomerRequest;
import com.customer.grpc.CustomerResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;


import java.util.Optional;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class CustomerGrpcService extends CustomerAccessServiceGrpc.CustomerAccessServiceImplBase {

    private final CustomerService service;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Utilities utilities;

    @Override
    public void addCustomer(CustomerRequest request, StreamObserver<CustomerResponse> responseObserver) {
        super.addCustomer(request, responseObserver);
    }

    @Override
    public void updateCustomer(CustomerRequest request, StreamObserver<CustomerResponse> responseObserver) {
        super.updateCustomer(request, responseObserver);
    }

    @Override
    public void disableCustomer(BusinessPartner request, StreamObserver<CustomerResponse> responseObserver) {
        super.disableCustomer(request, responseObserver);
    }

    @Override
    public void getCustomer(BusinessPartner request, StreamObserver<CustomerResponse> responseObserver) {
        String businessPartner = request.getBusinessPartner();
        try {


            Optional<Customer> customer = service.getCustomerByBusinessPartner(businessPartner);
            CustomerDTO dto = utilities.setDTO(customer);
            CustomerResponse response = CustomerResponse.newBuilder()
                    .setId(dto.getId())
                    .setBusinessPartner(dto.getBusinessPartner())
                    .setName(dto.getName())
                    .setIdDocumentList(dto.getIdDocumentList())
                    .setAddress(dto.getAddress())
                    .setPhone(dto.getPhone())
                    .setIdCustomerStatusList(dto.getIdCustomerStatusList())
                    .setIdCustomerTypeList(dto.getIdCustomerTypeList())
                    .setIdCustomerLevelList(dto.getIdCustomerLevelList())
                    .setIdGroup(dto.getIdGroup())
                    .setEmailAddress(dto.getEmailAddress())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (RuntimeException e) {
            log.error("Error " + e.getMessage());
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        } catch (Exception e) {
            log.error("Error " + e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("An unexpected error occurred")
                    .withCause(e)
                    .asRuntimeException());
        }
    }

}
