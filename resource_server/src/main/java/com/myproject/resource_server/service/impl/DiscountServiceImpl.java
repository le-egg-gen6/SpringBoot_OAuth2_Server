package com.myproject.resource_server.service.impl;

import com.myproject.resource_server.error.exception.InvalidArgumentException;
import com.myproject.resource_server.error.exception.ResourceNotFoundException;
import com.myproject.resource_server.model.Cart;
import com.myproject.resource_server.model.Discount;
import com.myproject.resource_server.repository.DiscountRepository;
import com.myproject.resource_server.service.CartService;
import com.myproject.resource_server.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CartService cartService;

    @Override
    public void applyDiscount(String code) {
        Discount discount = discountRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Discount code not found"));

        if (discount.getStatus() != 1) {
            throw new InvalidArgumentException("Discount code is expired!");
        }

        Cart cart = cartService.getCart();

        cart.setDiscount(discount);
        cart = cartService.calculatePrice(cart);
        cartService.saveCart(cart);
    }

}
