package com.example.codegymfoods.repository.employee;

import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findAllByNameContainingAndPositionId(String name, Integer id, Pageable pageable);

    Page<Employee> findAllByNameContaining(String name, Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByAppUser_UserName(String userName);

    Employee findCustomerByAppUser_UserName(String account);
}

