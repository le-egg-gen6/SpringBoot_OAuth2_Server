package com.myproject.resource_server.service.cache.impl;

import com.myproject.resource_server.model.ProductVariant;
import com.myproject.resource_server.repository.ProductVariantRepository;
import com.myproject.resource_server.service.cache.ProductVariantCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product_variant")
public class ProductVariantCacheServiceImpl implements ProductVariantCacheService {

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Override
    @Cacheable(key = "{#root.methodName,#id}")
    public ProductVariant findById(Long id) {
        return productVariantRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<ProductVariant> findTop8ByOrderBySellCountDesc() {
        return productVariantRepository.findTop8ByOrderBySellCountDesc();
    }
}

