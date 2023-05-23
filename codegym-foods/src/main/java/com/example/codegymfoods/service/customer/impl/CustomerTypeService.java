package com.example.codegymfoods.service.customer.impl;

import com.example.codegymfoods.model.customer.CustomerType;
import com.example.codegymfoods.repository.customer.ICustomerTypeRepository;
import com.example.codegymfoods.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository customerTypeRepository;
    @Override
    public List<CustomerType> findAllCustomerType() {
        return (List<CustomerType>) customerTypeRepository.findAll();
    }

    @Override
    public CustomerType findCustomerTypeById(Integer id) {
        return customerTypeRepository.findById(id).get();
    }
}
