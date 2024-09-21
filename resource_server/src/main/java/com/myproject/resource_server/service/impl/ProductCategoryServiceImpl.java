package com.myproject.resource_server.service.impl;

import com.myproject.resource_server.converter.category.ProductCategoryResponseConverter;
import com.myproject.resource_server.error.exception.ResourceNotFoundException;
import com.myproject.resource_server.model.ProductCategory;
import com.myproject.resource_server.payload.response.category.ProductCategoryResponse;
import com.myproject.resource_server.service.ProductCategoryService;
import com.myproject.resource_server.service.cache.ProductCategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryCacheService productCategoryCacheService;

    @Autowired
    private ProductCategoryResponseConverter productCategoryResponseConverter;

    @Override
    public List<ProductCategoryResponse> findAllByOrderByName() {
        List<ProductCategory> productCategories = productCategoryCacheService.findAllByOrderByName();
        if (productCategories.isEmpty()) {
            throw new ResourceNotFoundException("Could not find product categories");
        }
        return productCategories
                .stream()
                .map(productCategoryResponseConverter)
                .collect(Collectors.toList());
    }

}
