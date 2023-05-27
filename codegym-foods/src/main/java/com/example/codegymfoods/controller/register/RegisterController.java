package com.example.codegymfoods.controller.register;

import com.example.codegymfoods.dto.CustomerDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.login.AppRole;
import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;
import com.example.codegymfoods.service.customer.impl.CustomerService;
import com.example.codegymfoods.service.customer.impl.UserRoleService;
import com.example.codegymfoods.utils.EncrytedPasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller

public class RegisterController extends EncrytedPasswordUtils {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EncrytedPasswordUtils encrytedPasswordUtils;
    @Autowired
    private UserRoleService userRoleService;
    @GetMapping("/")
    public String registerForm(Model model) {
        model.addAttribute("customerDto", new CustomerDTO());
        return "/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("customerDto") CustomerDTO customerCreateDTO, BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "/register";
        } else if (customerService.existsByEmail(customerCreateDTO.getEmail())) {
            model.addAttribute("message", "Email đã tồn tại, vui lòng nhập email khác");
            return "/register";
        } else if (customerService.existsByPhoneNumber(customerCreateDTO.getPhoneNumber())) {
            model.addAttribute("message1", "Số điện thoại đã tồn tại, vui lòng nhập số điện thoại khác");
            return "/register";
        } else if (customerService.existsByAppUser_UserName(customerCreateDTO.getAppUser().getUserName())) {
            model.addAttribute("message2", "Tài khoản đã tồn tại, vui lòng nhập tài khoản khác");
            return "/register";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerCreateDTO, customer);
            customer.getAppUser().setEncrytedPassword(encrytePassword(customer.getAppUser().getEncrytedPassword()));
            customerService.saveCustomer(customer);
            AppUser appUser = customer.getAppUser();
            AppRole appRole = new AppRole(2, "ROLE_USER");
            userRoleService.saveUserRole(new UserRole(appUser, appRole));
            redirect.addFlashAttribute("msg", "Đăng ký thành công");
            return "redirect:/login";
        }
    }
}