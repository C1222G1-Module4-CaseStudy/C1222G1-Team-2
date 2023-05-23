package com.example.codegymfoods.controller.customer;

import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.service.customer.ICustomerService;
import com.example.codegymfoods.service.customer.ICustomerTypeService;
import com.example.codegymfoods.service.customer.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/customer/list";
    }
    @GetMapping("/update/{idCustomer}")
    public String showUpdateRegisterForm(@PathVariable Integer idCustomer, Model model) {
        model.addAttribute("customerDto", customerService.findByIdCustomer(idCustomer));
        model.addAttribute("customerType", customerTypeService.findAllCustomerType());
        return "register_update";
    }
}
