package com.example.codegymfoods.controller.customer;

import com.example.codegymfoods.dto.CustomerDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;
import com.example.codegymfoods.service.customer.ICustomerService;
import com.example.codegymfoods.service.customer.ICustomerTypeService;
import com.example.codegymfoods.service.customer.IUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerTypeService customerTypeService;
    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("")
    public String list(Model model, @PageableDefault(size = 4) Pageable pageable,
                       @RequestParam(defaultValue = "" , required = false) String name) {
        Sort sort = Sort.by("idCustomer").descending();
        model.addAttribute("name", name);
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Customer> customerPage = customerService.findAllCustomer(name, sortedPage);
        model.addAttribute("customerList", customerPage);
        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i < customerPage.getTotalPages(); i++) {
            integerList.add(i);
        }
        integerList.add(integerList.size()+1);
        model.addAttribute("check" , 0);
        model.addAttribute("customerTypeList", customerTypeService.findAllCustomerType());
        model.addAttribute("integerList", integerList);
        model.addAttribute("typeOfCustomer", customerTypeService.findCustomerTypeById(1).getName());
        return "/admin/customer/list";
    }
    @GetMapping("/update/{idCustomer}")
    public String showUpdateRegisterForm(@PathVariable Integer idCustomer, Model model) {
        model.addAttribute("customerDto", customerService.findByIdCustomer(idCustomer));
        model.addAttribute("customerType", customerTypeService.findAllCustomerType());
        return "register_update";
    }
    @PostMapping("/update")
    public String updateRegister(@Valid @ModelAttribute("customerDto") CustomerDTO customerUpdateDTO, BindingResult bindingResult, Model model, RedirectAttributes redirect) {

        Integer id = customerUpdateDTO.getIdCustomer();
        Customer customerOld = customerService.findByIdCustomer(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerType", customerTypeService.findAllCustomerType());
            return "register_update";
        } else if (customerService.existsByEmail(customerUpdateDTO.getEmail()) && (!customerOld.getEmail().equals(customerUpdateDTO.getEmail()))) {
            model.addAttribute("message", "Email đã tồn tại, vui lòng nhập email khác");
            model.addAttribute("customerType", customerTypeService.findAllCustomerType());
            return "register_update";
        } else if (customerService.existsByPhone(customerUpdateDTO.getPhoneNumber()) && (!customerOld.getPhoneNumber().equals(customerUpdateDTO.getPhoneNumber()))) {
            model.addAttribute("message", "Số điện thoại đã tồn tại, vui lòng nhập số điện thoại khác");
            model.addAttribute("customerType", customerTypeService.findAllCustomerType());
            return "register_update";
        } else if (customerService.existsByAppUser_UserName(customerUpdateDTO.getAppUser().getUserName()) && (!customerOld.getAppUser().getUserName().equals(customerUpdateDTO.getAppUser().getUserName()))) {
            model.addAttribute("message", "Tài khoản đã tồn tại, vui lòng nhập tài khoản khác");
            model.addAttribute("customerType", customerTypeService.findAllCustomerType());
            return "register_update";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerUpdateDTO, customer);
            customerService.saveCustomer(customer);
            redirect.addFlashAttribute("msg", "Cập nhật thành công");
            return "redirect:/customer";

        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam(name = "delete") Integer idCustomer, RedirectAttributes redirect) {
        Customer customer = customerService.findByIdCustomer(idCustomer);
        AppUser appUser = customer.getAppUser();
        UserRole userRole = userRoleService.findUserRoleByAppUser(appUser);
        userRoleService.deleteUserRole(userRole);
        customerService.deleteCustomer(idCustomer);
        redirect.addFlashAttribute("msg", "Xóa thành công");
        return "redirect:/customer";
    }

    @GetMapping("/type/{id}")
    public String searchByCustomerType(@PathVariable Integer id, Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("customerList", customerService.findByCustomerType(id, PageRequest.of(page, 4)));
        model.addAttribute("customerTypeList", customerTypeService.findAllCustomerType());
        model.addAttribute("totalElement", customerService.findByCustomerType(id, PageRequest.of(page, 4)).getTotalPages());
        model.addAttribute("typeOfCustomer", customerTypeService.findCustomerTypeById(id).getName());
        return "/admin/customer/list";
    }
}
