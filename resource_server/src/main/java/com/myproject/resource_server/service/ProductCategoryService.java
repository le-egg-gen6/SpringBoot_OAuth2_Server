package com.myproject.resource_server.service;

import com.myproject.resource_server.payload.response.category.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryResponse> findAllByOrderByName();

}
