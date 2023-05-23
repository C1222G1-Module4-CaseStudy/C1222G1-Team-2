package com.example.codegymfoods.service.customer;

import com.example.codegymfoods.model.customer.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    List<CustomerType> findAllCustomerType();
    CustomerType findCustomerTypeById(Integer id);
}
