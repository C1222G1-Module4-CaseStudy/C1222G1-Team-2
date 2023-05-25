package com.example.codegymfoods.service.employee.impl;


import com.example.codegymfoods.model.employee.Employee;
import com.example.codegymfoods.repository.employee.IEmployeeRepository;
import com.example.codegymfoods.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> findAllByName(String name, Pageable pageable) {
        return this.employeeRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Page<Employee> findAllByNameByPositionId(String name, Integer id, Pageable pageable) {
        return this.employeeRepository.findAllByNameContainingAndPositionId(name, id, pageable);
    }

    @Override
    public void delete(Integer id, Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByAppUser_UserName(String userName) {
        return employeeRepository.existsByAppUser_UserName(userName);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findCustomerByAppUser_UserName(username);
    }
}
