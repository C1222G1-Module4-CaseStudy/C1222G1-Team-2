package com.example.codegymfoods.model.login;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "app_user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "app_user_uk", columnNames = "user_name") })
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", length = 36, nullable = false)
    @NotBlank(message = "Tài khoản không được để trống")
    private String userName;

    @Column(name = "encrypt_password", length = 128, nullable = false)
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải trên 6 ký tự")
    private String encrytedPassword;

    public AppUser() {
    }

    @Column(name = "enabled", length = 1, nullable = false)
    @Value("${some.key: true}")
    private boolean enabled;

    public AppUser(Long userId, String userName, String encrytedPassword, boolean enabled) {
        this.userId = userId;
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}
