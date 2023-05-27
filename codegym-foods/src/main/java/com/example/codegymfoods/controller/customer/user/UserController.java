package com.example.codegymfoods.controller.customer.user;

import com.example.codegymfoods.dto.customer.CustomerDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.service.customer.impl.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static com.example.codegymfoods.utils.EncrytedPasswordUtils.encrytePassword;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/detail")
    public String showDetail(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("customerList", customerService.findByUsername(user.getUsername()));
        return "userInfoPage";
    }

    @GetMapping("/update")
    public String showUpdateRegisterForm(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUsername(user.getUsername());
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
//        customerDTO.setIdCustomer(customer.getId());
        model.addAttribute("customerDto",customerDTO );
        return "register_update";
    }

    @PostMapping("/update")
    public String updateRegister(@Valid @ModelAttribute("customerDto") CustomerDTO customerUpdateDTO, BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        Integer id = customerUpdateDTO.getId();
        Customer customerOld = customerService.findByIdCustomer(id);
        if (bindingResult.hasErrors()) {
            return "register_update";
        } else if (customerService.existsByEmail(customerUpdateDTO.getEmail()) && (!customerOld.getEmail().equals(customerUpdateDTO.getEmail()))) {
            model.addAttribute("message", "Email đã tồn tại, vui lòng nhập email khác");
            return "register_update";
        } else if (customerService.existsByPhoneNumber(customerUpdateDTO.getPhoneNumber()) && (!customerOld.getPhoneNumber().equals(customerUpdateDTO.getPhoneNumber()))) {
            model.addAttribute("message1", "Số điện thoại đã tồn tại, vui lòng nhập số điện thoại khác");
            return "register_update";
        } else if (customerService.existsByAppUser_UserName(customerUpdateDTO.getAppUser().getUserName()) && (!customerOld.getAppUser().getUserName().equals(customerUpdateDTO.getAppUser().getUserName()))) {
            model.addAttribute("message2", "Tài khoản đã tồn tại, vui lòng nhập tài khoản khác");
            return "register_update";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerUpdateDTO, customer);
            customer.getAppUser().setEncrytedPassword(encrytePassword(customer.getAppUser().getEncrytedPassword()));
            customerService.saveCustomer(customer);
            redirect.addFlashAttribute("msg", "Cập nhật thành công");
            return "redirect:/user/detail";
        }
    }

}
