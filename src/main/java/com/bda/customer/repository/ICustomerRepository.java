package com.bda.customer.repository;

import com.bda.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT C " +
            "FROM Customer C " +
            "WHERE C.businessPartner = :businessPartner ")
    Customer findCustomerByBusinessPartner(@Param("businessPartner") String businessPartner);
}
