package com.example.codegymfoods.dto.employee;

import com.example.codegymfoods.model.login.AppUser;
import javax.validation.Valid;
import com.example.codegymfoods.service.employee.annotation.YearOldValid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CustomerDTO {
    private Integer idCustomer;
    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Ngày sinh không được để trống")
    @NotBlank(message = "Không được để trống")
    private String address;
    @NotBlank(message = "Không được để trống")
    private String phoneNumber;
    @NotBlank(message = "Không được để trống")
    @Email(message = "Email chưa đúng định dạng. Vui lòng kiểm tra lại")
    private String email;
    @NotBlank(message = "Không được để trống")
    @YearOldValid
    private String dateOfBirth;
    @NotBlank(message = " Đia chỉ không được để trống")
    private String address;
    @NotBlank(message = "Email không được để trống")
    @Email
    private String email;
    @NotBlank(message = "Số điện thoai không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại có 10 số")
    private String phoneNumber;

    @Valid
    private AppUser appUser;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer idCustomer, String name, String dateOfBirth, String address, String email, String phoneNumber, AppUser appUser) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
