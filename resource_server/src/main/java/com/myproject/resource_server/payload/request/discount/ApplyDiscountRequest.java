package com.myproject.resource_server.payload.request.discount;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplyDiscountRequest {

    @NotBlank
    private String code;

}

