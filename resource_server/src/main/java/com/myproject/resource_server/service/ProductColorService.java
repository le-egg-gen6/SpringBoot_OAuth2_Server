package com.myproject.resource_server.service;

import com.myproject.resource_server.payload.response.color.ProductColorResponse;

import java.util.List;

public interface ProductColorService {

    List<ProductColorResponse> findAll();

}
