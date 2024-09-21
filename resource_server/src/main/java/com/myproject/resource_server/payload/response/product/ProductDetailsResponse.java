package com.myproject.resource_server.payload.response.product;

import com.myproject.resource_server.dto.CategoryDTO;
import com.myproject.resource_server.dto.ProductVariantDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsResponse {

    private String name;

    private String url;

    private String sku;

    private String longDesc;

    private CategoryDTO category;

    private List<ProductVariantDetailDTO> productVariantDetails;

}
