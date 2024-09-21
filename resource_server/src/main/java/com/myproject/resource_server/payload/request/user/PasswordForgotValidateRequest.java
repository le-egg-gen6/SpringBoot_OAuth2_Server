package com.myproject.resource_server.payload.request.user;

import com.myproject.resource_server.validator.PasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatches
public class PasswordForgotValidateRequest {

    @NotBlank
    private String token;

    @NotBlank
    @Size(min = 6, max = 52)
    private String newPassword;

    @NotBlank
    private String newPasswordConfirm;

}