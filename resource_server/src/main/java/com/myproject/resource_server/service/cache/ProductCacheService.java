package com.myproject.resource_server.service.cache;

import com.myproject.resource_server.model.Product;
import com.myproject.resource_server.model.ProductCategory;

import java.util.List;

public interface ProductCacheService {

    Product findByUrl(String url);

    List<Product> findTop8ByOrderByDateCreatedDesc();

    List<Product> getRelatedProducts(ProductCategory productCategory, Long id);

}
