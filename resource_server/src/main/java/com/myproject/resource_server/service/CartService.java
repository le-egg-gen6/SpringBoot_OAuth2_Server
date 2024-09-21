package com.myproject.resource_server.service;

import com.myproject.resource_server.model.Cart;
import com.myproject.resource_server.payload.request.cart.ConfirmCartRequest;
import com.myproject.resource_server.payload.response.cart.CartResponse;

public interface CartService {

    CartResponse addToCart(Long id, Integer amount);

    CartResponse incrementCartItem(Long cartItemId, Integer amount);

    CartResponse decrementCartItem(Long cartItemId, Integer amount);

    CartResponse fetchCart();

    CartResponse removeFromCart(Long id);

    boolean confirmCart(ConfirmCartRequest confirmCartRequest);

    Cart getCart();

    void saveCart(Cart cart);

    void emptyCart();

    Cart calculatePrice(Cart cart);

}
