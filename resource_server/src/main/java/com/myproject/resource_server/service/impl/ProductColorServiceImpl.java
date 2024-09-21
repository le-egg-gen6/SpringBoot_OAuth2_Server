package com.myproject.resource_server.service.impl;

import com.myproject.resource_server.converter.color.ProductColorResponseConverter;
import com.myproject.resource_server.error.exception.ResourceNotFoundException;
import com.myproject.resource_server.model.Color;
import com.myproject.resource_server.payload.response.color.ProductColorResponse;
import com.myproject.resource_server.service.ProductColorService;
import com.myproject.resource_server.service.cache.ProductColorCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductColorServiceImpl implements ProductColorService {

    @Autowired
    private ProductColorCacheService productColorCacheService;

    @Autowired
    private ProductColorResponseConverter productColorResponseConverter;

    @Override
    public List<ProductColorResponse> findAll() {
        List<Color> productColors = productColorCacheService.findAll();
        if (productColors.isEmpty()) {
            throw new ResourceNotFoundException("Could not find product colors");
        }
        return productColors
                .stream()
                .map(productColorResponseConverter)
                .collect(Collectors.toList());
    }

}
