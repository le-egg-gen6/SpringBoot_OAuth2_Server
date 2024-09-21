package com.myproject.resource_server.service;

import com.myproject.resource_server.model.User;
import com.myproject.resource_server.payload.request.user.PasswordForgotValidateRequest;

public interface TokenService {

    void createEmailConfirmToken(User user);

    void createPasswordResetToken(String email);

    void validateEmail(String token);

    void validateForgotPasswordConfirm(String token);

    void validateForgotPassword(PasswordForgotValidateRequest passwordForgotValidateRequest);

}
