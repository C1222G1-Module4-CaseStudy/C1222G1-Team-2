package com.example.codegymfoods.dto.employee;

import com.example.codegymfoods.dto.employee.annotation.YearOldValid;
import com.example.codegymfoods.model.employee.Position;
import com.example.codegymfoods.model.login.AppUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmployeeDTO {
    private Integer id;
    @NotBlank(message = "Vui lòng không được để trống")
    private String name;
    @NotBlank(message = "Vui lòng không được để trống")
    private String address;
    @NotBlank(message = "Vui lòng không được để trống")
    @Pattern(regexp = "\\b\\d{10,11}\\b", message = "Số điện thoại phải 10 hoặc 11 số")
    private String phoneNumber;
    @NotBlank(message = "Vui lòng không được để trống")
    @Email(message = "Email chưa đúng định dạng. Vui lòng kiểm tra lại")
    private String email;
    @NotBlank(message = "Vui lòng không được để trống")
    @YearOldValid
    private String dateOfBirth;
    private String avatar;
    private AppUser appUser;
    private Position position;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String name, String address, String phoneNumber, String email, String dateOfBirth, String avatar, AppUser appUser, Position position) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.appUser = appUser;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
