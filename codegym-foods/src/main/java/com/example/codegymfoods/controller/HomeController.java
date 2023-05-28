package com.example.codegymfoods.controller;

import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.model.customer.Customer;
import com.example.codegymfoods.model.employee.Employee;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.model.product.ProductType;
import com.example.codegymfoods.service.blog.IBlogService;
import com.example.codegymfoods.service.customer.ICustomerService;
import com.example.codegymfoods.service.employee.IEmployeeService;
import com.example.codegymfoods.service.product.IProductService;
import com.example.codegymfoods.service.product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/home")
@SessionAttributes(value = "cartDTO")
public class HomeController {
    @ModelAttribute(name = "cartDTO")
    private CartDTO initCartDTO() {
        return new CartDTO();
    }
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;
    @GetMapping("")
    public String index(Model model,
                        @PageableDefault() Pageable pageable, HttpServletRequest request) {
        model.addAttribute("blogList", this.iBlogService.getBlog(pageable));
        Page<Product> productList = productService.getBlogPage(pageable);
        List<ProductType> productTypeList = productTypeService.getAll();
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        model.addAttribute("request",request);
        return "index";
    }
    @GetMapping("/success")
    public String disPlay(Model model,
                          @PageableDefault() Pageable pageable, HttpServletRequest request) {
        model.addAttribute("blogList", this.iBlogService.getBlog(pageable));
        Page<Product> productList = productService.getBlogPage(pageable);
        List<ProductType> productTypeList = productTypeService.getAll();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.findByUsername(user.getUsername());
        Employee employee = employeeService.findByUsername(user.getUsername());
        model.addAttribute("customer",customer);
        model.addAttribute("employee",employee);
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        model.addAttribute("request", request);
        return "index";
    }
}
