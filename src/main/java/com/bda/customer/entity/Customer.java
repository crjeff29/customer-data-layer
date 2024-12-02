package com.bda.customer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Customers", schema = "SCHBDA")
public class Customer {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "businessPartner", nullable = false, length = 10)
    private String businessPartner;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "idDocumentList", nullable = false, length = 36)
    private String idDocumentList;

    @Column(name = "address", length = 250)
    private String address;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "idCustomerStatusList", nullable = false, length = 36)
    private String idCustomerStatusList;

    @Column(name = "idCustomerTypeList", nullable = false, length = 36)
    private String idCustomerTypeList;

    @Column(name = "idCustomerLevelList", nullable = false, length = 36)
    private String idCustomerLevelList;

    @Column(name = "idGroup", nullable = false, length = 36)
    private String idGroup;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateAt")
    private Date updateAt;

    @Column(name = "modifiedBy", length = 50)
    private String modifiedBy;

    @Column(name = "createdBy", length = 50)
    private String createdBy;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "emailAddress", length = 45)
    private String emailAddress;
}
