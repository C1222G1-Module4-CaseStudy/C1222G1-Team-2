package com.example.codegymfoods.model.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCustomerType;
    private String name;

    public CustomerType() {
    }

    public CustomerType(Integer idCustomerType, String name) {
        this.idCustomerType = idCustomerType;
        this.name = name;
    }

    public Integer getIdCustomerType() {
        return idCustomerType;
    }

    public void setIdCustomerType(Integer idCustomerType) {
        this.idCustomerType = idCustomerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

