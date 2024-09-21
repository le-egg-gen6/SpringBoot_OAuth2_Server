package com.myproject.resource_server.service;

import com.myproject.resource_server.payload.request.order.PostOrderRequest;
import com.myproject.resource_server.payload.response.order.OrderResponse;

import java.util.List;

public interface OrderService {

    Integer getAllOrdersCount();

    List<OrderResponse> getAllOrders(Integer page, Integer pageSize);

    OrderResponse postOrder(PostOrderRequest postOrderRequest);

}
