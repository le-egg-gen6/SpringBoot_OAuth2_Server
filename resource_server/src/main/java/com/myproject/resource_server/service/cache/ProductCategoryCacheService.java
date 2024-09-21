package com.myproject.resource_server.service.cache;

import com.myproject.resource_server.model.ProductCategory;

import java.util.List;

public interface ProductCategoryCacheService {

    List<ProductCategory> findAllByOrderByName();

}
