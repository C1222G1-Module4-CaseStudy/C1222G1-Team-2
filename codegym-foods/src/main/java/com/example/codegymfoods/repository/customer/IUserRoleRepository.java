package com.example.codegymfoods.repository.customer;

import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findUserRoleByAppUser(AppUser appUser);
}
