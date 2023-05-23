package com.example.codegymfoods.service.customer;

import com.example.codegymfoods.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    Page<Customer> findAllCustomer(String name, Pageable pageable);
    void saveCustomer(Customer customer);
    void deleteCustomer(Integer idCustomer);
    Customer findByIdCustomer(Integer idCustomer);
    Page<Customer> findByNameCustomer(String nameCustomer, PageRequest pageRequest);
    Page<Customer> findByCustomerType(Integer idCustomerType, Pageable pageable);
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByAppUser_UserName(String userName);

    Customer findByNameAccount(String nameAccount);
}
