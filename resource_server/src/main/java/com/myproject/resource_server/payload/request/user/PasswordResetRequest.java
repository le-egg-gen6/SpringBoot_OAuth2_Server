package com.myproject.resource_server.payload.request.user;

import com.myproject.resource_server.validator.PasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatches
public class PasswordResetRequest {

    @NotBlank
    @Size(min = 6, max = 52)
    private String oldPassword;

    @NotBlank
    @Size(min = 6, max = 52)
    private String newPassword;

    @NotBlank
    @Size(min = 6, max = 52)
    private String newPasswordConfirm;

}