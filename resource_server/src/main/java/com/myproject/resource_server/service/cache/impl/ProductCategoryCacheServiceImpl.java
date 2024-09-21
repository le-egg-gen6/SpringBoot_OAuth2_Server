package com.myproject.resource_server.service.cache.impl;

import com.myproject.resource_server.model.ProductCategory;
import com.myproject.resource_server.repository.ProductCategoryRepository;
import com.myproject.resource_server.service.cache.ProductCategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product_category")
public class ProductCategoryCacheServiceImpl implements ProductCategoryCacheService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<ProductCategory> findAllByOrderByName() {
        return productCategoryRepository.findAllByOrderByName();
    }

}
