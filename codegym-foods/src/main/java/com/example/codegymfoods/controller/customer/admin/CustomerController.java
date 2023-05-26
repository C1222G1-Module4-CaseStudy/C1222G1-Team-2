package com.example.codegymfoods.controller.customer.admin;

import com.example.codegymfoods.dto.CustomerDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.login.AppUser;
import com.example.codegymfoods.model.login.UserRole;
import com.example.codegymfoods.service.customer.ICustomerService;
import com.example.codegymfoods.service.customer.impl.CustomerService;
import com.example.codegymfoods.service.customer.impl.UserRoleService;
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
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("")
    public String list(Model model, @PageableDefault(size = 4) Pageable pageable,
                       @RequestParam(name = "search", defaultValue = "" , required = false) String name) {
        Sort sort = Sort.by("idCustomer").descending();
        model.addAttribute("name", name);
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Customer> customerPage = customerService.findByNameCustomer(name, sortedPage);
        model.addAttribute("customerList", customerPage);
//        model.addAttribute("list" , customerPage.getTotalElements());
        List<Integer> integerList = new ArrayList<>();
        for (int i = 1; i < customerPage.getTotalPages(); i++) {
            integerList.add(i);
        }
        integerList.add(integerList.size()+1);
//        model.addAttribute("check" , 0);
        model.addAttribute("integerList", integerList);
//        model.addAttribute("typeOfCustomer", customerTypeService.findCustomerTypeById(1).getName());
        return "admin/customer/list";
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
}
