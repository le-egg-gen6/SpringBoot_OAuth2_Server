package com.myproject.resource_server.service.cache.impl;

import com.myproject.resource_server.model.Color;
import com.myproject.resource_server.repository.ColorRepository;
import com.myproject.resource_server.service.cache.ProductColorCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product_color")
public class ProductColorCacheServiceImpl implements ProductColorCacheService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<Color> findAll() {
        return colorRepository.findAll();
    }
}
