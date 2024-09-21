package com.myproject.resource_server.payload.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordForgotConfirmRequest {

    @NotBlank
    private String token;

}
