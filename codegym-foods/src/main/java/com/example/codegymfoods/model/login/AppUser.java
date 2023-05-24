package com.example.codegymfoods.model.login;

import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.employee.Employee;

import javax.persistence.*;

@Entity
@Table(name = "app_user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "app_user_uk", columnNames = "user_name") })
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long id;


    @Column(name = "user_name", length = 36, nullable = false)
    private String userName;

    @Column(name = "encrypt_password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;
    @OneToOne(mappedBy = "appUser")
    private Employee employee;
    @OneToOne(mappedBy = "appUser")
    private Customer customer;
    public AppUser() {
    }

    public AppUser(Long id, String userName, String encrytedPassword, boolean enabled, Employee employee, Customer customer) {
        this.id = id;
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
        this.enabled = enabled;
        this.employee = employee;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
