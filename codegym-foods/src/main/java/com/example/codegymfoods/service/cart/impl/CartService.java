package com.example.codegymfoods.service.cart.impl;

import com.example.codegymfoods.dto.cart.CartDTO;
import com.example.codegymfoods.dto.product.ProductFromCartDTO;
import com.example.codegymfoods.model.product.Product;
import com.example.codegymfoods.service.cart.ICartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService implements ICartService {

    @Override
    public long totalBill(List<ProductFromCartDTO> productFromCartDTOList) {
        long totalBill =0;
        for (int i = 0; i < productFromCartDTOList.size(); i++) {
            totalBill+= productFromCartDTOList.get(i).getTotalPrice();
        }
        return totalBill;
    }

    @Override
    public void addToCart(Product product, CartDTO cartDTO) {
        boolean flag = false;
        for (Map.Entry<Integer, Integer> entry : cartDTO.getSelectedProducts().entrySet()) {
            if (entry.getKey().equals(product.getId())) {
                entry.setValue(entry.getValue() + 1);
                flag = true;
            }
        }
        if (!flag) {
            cartDTO.getSelectedProducts().put(product.getId(), 1);
        }
    }

    @Override
    public void changeQuantity(int id, int quantity, CartDTO cartDTO) {
        for (Map.Entry<Integer, Integer> entry : cartDTO.getSelectedProducts().entrySet()) {
            if (entry.getKey() == id) {
                if (quantity <= 0) {
                    Map<Integer, Integer> cartMap = cartDTO.getSelectedProducts();
                    cartMap.remove(id);
                    return;
                } else {
                    entry.setValue(quantity);
                }
            }
        }
    }
}
