package com.myproject.resource_server.payload.response.cart;

import com.myproject.resource_server.dto.CartItemDTO;
import com.myproject.resource_server.dto.DiscountDTO;
import lombok.Data;

import java.util.List;

@Data
public class CartResponse {

    private List<CartItemDTO> cartItems;

    private DiscountDTO discount;

    private Float totalCartPrice;

    private Float totalCargoPrice;

    private Float totalPrice;

}