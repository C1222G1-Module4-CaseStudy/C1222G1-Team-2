package com.example.codegymfoods.controller.customer.user;

import com.example.codegymfoods.dto.CustomerDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.service.customer.impl.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("detail/{idCustomer}")
    public String showDetail(@PathVariable Integer idCustomer, Model model){
        model.addAttribute("customerDto", customerService.findByIdCustomer(idCustomer));
        return "userInfoPage";
    }
    @GetMapping("/update/{idCustomer}")
    public String showUpdateRegisterForm(@PathVariable Integer idCustomer, Model model) {
        model.addAttribute("customerDto", customerService.findByIdCustomer(idCustomer));
        return "register_update";
    }

    @PostMapping("/update")
    public String updateRegister(@Valid @ModelAttribute("customerDto") CustomerDTO customerUpdateDTO, BindingResult bindingResult, Model model, RedirectAttributes redirect) {

        Integer id = customerUpdateDTO.getIdCustomer();
        Customer customerOld = customerService.findByIdCustomer(id);
        if (bindingResult.hasErrors()) {
            return "register_update";
        } else if (customerService.existsByEmail(customerUpdateDTO.getEmail()) && (!customerOld.getEmail().equals(customerUpdateDTO.getEmail()))) {
            model.addAttribute("message", "Email đã tồn tại, vui lòng nhập email khác");
            return "register_update";
        } else if (customerService.existsByPhoneNumber(customerUpdateDTO.getPhoneNumber()) && (!customerOld.getPhoneNumber().equals(customerUpdateDTO.getPhoneNumber()))) {
            model.addAttribute("message", "Số điện thoại đã tồn tại, vui lòng nhập số điện thoại khác");
            return "register_update";
        } else if (customerService.existsByAppUser_UserName(customerUpdateDTO.getAppUser().getUserName()) && (!customerOld.getAppUser().getUserName().equals(customerUpdateDTO.getAppUser().getUserName()))) {
            model.addAttribute("message", "Tài khoản đã tồn tại, vui lòng nhập tài khoản khác");
            return "register_update";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerUpdateDTO, customer);
            customerService.saveCustomer(customer);
            redirect.addFlashAttribute("msg", "Cập nhật thành công");
            return "redirect:/userInfoPage";
        }
    }
}
