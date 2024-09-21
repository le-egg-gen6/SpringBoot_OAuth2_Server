package com.myproject.resource_server.validator;

import com.myproject.resource_server.payload.request.user.PasswordForgotValidateRequest;
import com.myproject.resource_server.payload.request.user.PasswordResetRequest;
import com.myproject.resource_server.payload.request.user.RegisterUserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof RegisterUserRequest) {
            RegisterUserRequest registerUserRequest = (RegisterUserRequest) obj;
            return registerUserRequest.getPassword().equals(registerUserRequest.getPasswordRepeat());
        } else if (obj instanceof PasswordResetRequest) {
            PasswordResetRequest passwordResetRequest = (PasswordResetRequest) obj;
            return passwordResetRequest.getNewPassword().equals(passwordResetRequest.getNewPasswordConfirm());
        } else if (obj instanceof PasswordForgotValidateRequest) {
            PasswordForgotValidateRequest passwordForgotValidateRequest = (PasswordForgotValidateRequest) obj;
            return passwordForgotValidateRequest.getNewPassword().equals(passwordForgotValidateRequest.getNewPasswordConfirm());
        }
        return false;
    }
}
