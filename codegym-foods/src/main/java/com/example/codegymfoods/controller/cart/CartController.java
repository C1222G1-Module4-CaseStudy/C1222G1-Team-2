package com.example.codegymfoods.controller.cart;


import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.dto.product.ProductFromCartDTO;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.model.product.ProductType;
import com.example.codegymfoods.service.cart.IProductFromCartService;
import com.example.codegymfoods.service.product.IProductService;
import com.example.codegymfoods.service.product.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")

public class CartController {

    @Autowired
   private IProductFromCartService productFromCartService;
    @Autowired
  private   IProductService productService;
    @Autowired
   private IProductTypeService productTypeService;
    private static final String SEPARATOR = "-";
    @GetMapping("")
    public String disPlayBlog(Model model, @PageableDefault() Pageable pageable) {
        Page<Product> productList = productService.getBlogPage(pageable);
        List<ProductType> productTypeList = productTypeService.getAll();
        model.addAttribute("productTypeList", productTypeList);
        model.addAttribute("productList", productList);
        return "/index";
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(name = "id") int id, @SessionAttribute(name = "cartDTO") CartDTO cartDTO, Model model) {
        Product product = productService.getById(id);
        boolean flag = false;
        for (Map.Entry<Integer, Integer> entry : cartDTO.getSelectedProducts().entrySet()
        ) {
            if (entry.getKey() == product.getId()) {
                entry.setValue(entry.getValue() + 1);
                flag = true;
            }
        }
        if (!flag) {
            cartDTO.getSelectedProducts().put(product.getId(), 1);
        }

        return "redirect:/cart";
    }

    @GetMapping("/changeQuantity")
    public String changeQuantity(@RequestParam(value = "id") int id
            , @RequestParam(value = "quantiry") int quantity , @SessionAttribute(name = "cartDTO") CartDTO cartDTO) {
        for (Map.Entry<Integer, Integer> entry : cartDTO.getSelectedProducts().entrySet()
        ) {
            if (entry.getKey() == id) {
                if(quantity<=0){
                    Map<Integer,Integer> cartMap = cartDTO.getSelectedProducts();
                    cartMap.remove(id);
                    return "redirect:/cart";
                }else {
                    entry.setValue(quantity);}
            }
        }
        return "redirect:/cart";
    }
    @GetMapping("/deleteInCart")
    public String delete(@RequestParam(value = "idDelete") int id,@SessionAttribute(name = "cartDTO") CartDTO cartDTO) {
        Map<Integer,Integer> cartMap = cartDTO.getSelectedProducts();
        cartMap.remove(id);
        return "redirect:/cart";
    }
    @GetMapping("/home")
    public String getProductsFromCart(@SessionAttribute(name = "cartDTO") CartDTO cartDTO, Model model) {
        Set<Integer> productsIds = cartDTO.getSelectedProducts().keySet();
        Map<Integer, Product> mapProducts = productService.getProductsById(productsIds).stream().collect(Collectors.toMap(Product::getId, product -> product));
        List<ProductFromCartDTO> productFromCartDTOList = cartDTO.getSelectedProducts().entrySet().stream()
                .map(e -> new ProductFromCartDTO(e.getKey(),
                        mapProducts.get(e.getKey()).getPicture(),
                        mapProducts.get(e.getKey()).getName(),
                        mapProducts.get(e.getKey()).getPrice(),
                        e.getValue(),
                        mapProducts.get(e.getKey()).getPrice()*e.getValue()))
                .collect(Collectors.toCollection(LinkedList::new));
//        long totalBill = serviceProductFromCart.totalBill(productFromCartDTOList);
//        model.addAttribute("totalBill",totalBill);
        model.addAttribute("productFromCartDTOList", productFromCartDTOList);
        return "/cart";
    }
}
