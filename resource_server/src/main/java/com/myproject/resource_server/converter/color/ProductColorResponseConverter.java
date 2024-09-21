package com.myproject.resource_server.converter.color;

import com.myproject.resource_server.dto.ColorDTO;
import com.myproject.resource_server.model.Color;
import com.myproject.resource_server.payload.response.color.ProductColorResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductColorResponseConverter implements Function<Color, ProductColorResponse> {

    @Override
    public ProductColorResponse apply(Color color) {
        ProductColorResponse productColorResponse = new ProductColorResponse();
        productColorResponse.setColor(ColorDTO.builder().name(color.getName()).hex(color.getHex()).build());
        return productColorResponse;
    }

}

