package com.example.codegymfoods.dto;

import com.example.codegymfoods.model.customer.CustomerType;
import com.example.codegymfoods.model.login.AppUser;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerDTO {
    private Integer idCustomer;
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Ngày sinh không được để trống")
    private String dateOfBirth;
    @NotBlank(message = " Đia chỉ không được để trống")
    private String address;
    @NotBlank(message = "Email không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Không đúng định dạng example@gmail")
    private String email;
    @NotBlank(message = "Số điện thoai không được để trống")
    @Pattern(regexp = "\\d{9,10}", message = "Số điện thoại phải từ 9 đến 10 số")
    private String phoneNumber;


    private CustomerType customerType;
    @Valid
    private AppUser appUser;

    public CustomerDTO() {
    }


    public CustomerDTO(String name, String dateOfBirth, String address, String email, String phoneNumber, CustomerType customerType, AppUser appUser) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.appUser = appUser;
    }

    public CustomerDTO(Integer idCustomer, String name, String dateOfBirth, String address, String email, String phoneNumber, CustomerType customerType, AppUser appUser) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.appUser = appUser;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
