package com.bda.customer.dto;

import lombok.Data;



@Data
public class CustomerDTO {
    private String id;
    private String businessPartner;
    private String name;
    private String idDocumentList;
    private String address;
    private String phone;
    private String idCustomerStatusList;
    private String idCustomerTypeList;
    private String idCustomerLevelList;
    private String idGroup;
    private String emailAddress;
}
