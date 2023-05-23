package com.example.codegymfoods.repository.customer;

import com.example.codegymfoods.model.customer.CustomerType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICustomerTypeRepository extends PagingAndSortingRepository<CustomerType, Integer> {
}
