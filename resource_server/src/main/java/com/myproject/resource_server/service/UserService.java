package com.myproject.resource_server.service;

import com.myproject.resource_server.model.User;
import com.myproject.resource_server.payload.request.user.PasswordResetRequest;
import com.myproject.resource_server.payload.request.user.RegisterUserRequest;
import com.myproject.resource_server.payload.request.user.UpdateUserAddressRequest;
import com.myproject.resource_server.payload.request.user.UpdateUserRequest;
import com.myproject.resource_server.payload.response.user.UserResponse;

public interface UserService {

    User register(RegisterUserRequest registerUserRequest);

    UserResponse fetchUser();

    User getUser();

    User saveUser(User user);

    User findByEmail(String email);

    boolean userExists(String email);

    UserResponse updateUser(UpdateUserRequest updateUserRequest);

    UserResponse updateUserAddress(UpdateUserAddressRequest updateUserAddressRequest);

    void resetPassword(PasswordResetRequest passwordResetRequest);

    Boolean getVerificationStatus();

}
