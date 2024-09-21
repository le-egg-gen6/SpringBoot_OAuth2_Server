package com.myproject.resource_server.service.cache;

import com.myproject.resource_server.model.ProductVariant;

import java.util.List;

public interface ProductVariantCacheService {

    ProductVariant findById(Long id);

    List<ProductVariant> findTop8ByOrderBySellCountDesc();

}
