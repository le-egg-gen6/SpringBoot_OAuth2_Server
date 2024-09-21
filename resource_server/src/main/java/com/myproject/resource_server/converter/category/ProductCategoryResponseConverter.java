package com.myproject.resource_server.converter.category;

import com.myproject.resource_server.dto.CategoryDTO;
import com.myproject.resource_server.model.ProductCategory;
import com.myproject.resource_server.payload.response.category.ProductCategoryResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductCategoryResponseConverter implements Function<ProductCategory, ProductCategoryResponse> {

    @Override
    public ProductCategoryResponse apply(ProductCategory productCategory) {
        ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
        productCategoryResponse.setCategory(CategoryDTO.builder().name(productCategory.getName()).build());
        return productCategoryResponse;
    }

}

