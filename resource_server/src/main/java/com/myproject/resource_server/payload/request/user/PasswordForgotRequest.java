package com.myproject.resource_server.payload.request.user;

import com.myproject.resource_server.validator.CustomEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordForgotRequest {

    @NotBlank
    @Size(min = 3, max = 52)
    @CustomEmail
    private String email;

}
