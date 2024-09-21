package com.myproject.resource_server.payload.response.product;

import com.myproject.resource_server.dto.ProductVariantDTO;
import lombok.Data;

@Data
public class ProductVariantResponse {

    private Long id;

    private String name;

    private String url;

    private ProductVariantDTO productVariant;

}