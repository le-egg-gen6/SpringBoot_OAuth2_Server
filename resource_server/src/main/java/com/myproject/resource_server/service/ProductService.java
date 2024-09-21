package com.myproject.resource_server.service;

import com.myproject.resource_server.model.ProductVariant;
import com.myproject.resource_server.payload.response.order.OrderResponse;
import com.myproject.resource_server.payload.response.product.ProductDetailsResponse;
import com.myproject.resource_server.payload.response.product.ProductResponse;
import com.myproject.resource_server.payload.response.product.ProductVariantResponse;

import java.util.List;

public interface ProductService {

    ProductDetailsResponse findByUrl(String url);

    List<ProductVariantResponse> getAll(Integer page, Integer size, String sort, String category, Float minPrice, Float maxPrice, String color);

    Long getAllCount(String category, Float minPrice, Float maxPrice, String color);

    ProductVariant findProductVariantById(Long id);

    List<ProductResponse> getRelatedProducts(String url);

    List<ProductResponse> getNewlyAddedProducts();

    List<ProductVariantResponse> getMostSelling();

    List<ProductResponse> getInterested();

    List<ProductResponse> searchProductDisplay(String keyword, Integer page, Integer size);

}
