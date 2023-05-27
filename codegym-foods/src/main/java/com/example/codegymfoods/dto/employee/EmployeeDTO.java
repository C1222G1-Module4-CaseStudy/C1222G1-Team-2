package com.example.codegymfoods.dto.employee;

import com.example.codegymfoods.model.employee.Position;
import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.service.employee.annotation.YearOldValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.xml.crypto.Data;
import java.util.Date;

public class EmployeeDTO {
    private Integer id;
    @NotBlank(message = "Không được để trống")
    private String name;
    @NotBlank(message = "Không được để trống")
    private String address;
    @NotBlank(message = "Không được để trống")
    private String phoneNumber;
    @NotBlank(message = "Không được để trống")
    @Email(message = "Email chưa đúng định dạng. Vui lòng kiểm tra lại")
    private String email;
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
