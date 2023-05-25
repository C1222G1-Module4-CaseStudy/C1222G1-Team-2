package com.example.codegymfoods.controller;

import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.model.product.ProductType;
import com.example.codegymfoods.service.blog.IBlogService;
import com.example.codegymfoods.service.product.IProductService;
import com.example.codegymfoods.service.product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    private IBlogService iBlogService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductTypeService productTypeService;
    @GetMapping("")
    public String index(Model model,
                        @PageableDefault(size = 3) Pageable pageable) {
        model.addAttribute("blogList", this.iBlogService.getBlog(pageable));
        Page<Product> productList = productService.getBlogPage(pageable);
        List<ProductType> productTypeList = productTypeService.getAll();
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        return "index";
    }
}
