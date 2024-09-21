package com.myproject.resource_server.payload.response.product;


import com.myproject.resource_server.dto.ProductVariantDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {

    private String name;

    private String url;

    private List<ProductVariantDTO> productVariants;

}
