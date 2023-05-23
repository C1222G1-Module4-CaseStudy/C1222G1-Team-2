package com.example.codegymfoods.repository.customer;

import com.example.codegymfoods.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
    @Query(value = "select * from customer where name_customer like concat('%',:name_customer,'%')", nativeQuery = true)
    Page<Customer> findByNameCustomer(@Param("name_customer") String name, PageRequest pageRequest);
    @Query(value = "select * from customer where customer_type_id = :customer_type_id", nativeQuery = true)
    Page<Customer> findByCustomerType(@Param("customer_type_id") Integer id, Pageable pageable);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByAppUser_UserName(String userName);
    Customer findCustomerByAppUser_UserName(String account);
    Page<Customer>findByNameContaining(String name , Pageable pageable);
}
