package com.myproject.resource_server.service.cache.impl;

import com.myproject.resource_server.model.Product;
import com.myproject.resource_server.model.ProductCategory;
import com.myproject.resource_server.repository.ProductRepository;
import com.myproject.resource_server.service.cache.ProductCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product")
public class ProductCacheServiceImpl implements ProductCacheService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Cacheable(key = "#url")
    public Product findByUrl(String url) {
        return productRepository.findByUrl(url).orElse(null);
    }


    @Override
    @Cacheable(key = "#root.methodName", unless = "#result.size()==0")
    public List<Product> findTop8ByOrderByDateCreatedDesc() {
        return productRepository.findTop8ByOrderByDateCreatedDesc();
    }

    @Override
    @Cacheable(key = "{#productCategory.name,#id}", unless = "#result.size()==0")
    public List<Product> getRelatedProducts(ProductCategory productCategory, Long id) {
        List<Product> productList = productRepository.findTop8ByProductCategoryAndIdIsNot(productCategory, id);
        if (productList.size() < 8) {
            productList.addAll(productRepository.findAllByProductCategoryIsNot(productCategory, PageRequest.of(0, 8 - productList.size())));
        }
        return productList;
    }

}

