package com.example.codegymfoods.service.employee;

import com.example.codegymfoods.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEmployyeService {
    Page<Employee> findAllByName(String name, Pageable pageable);

    Page<Employee> findAllByNameByPositionId(String name, Integer id, Pageable pageable);

    void delete(Integer id, Employee employee);

    Optional<Employee> findById(Integer id);

    void save(Employee employee);
}
