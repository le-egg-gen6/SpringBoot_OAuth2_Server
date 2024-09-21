package com.myproject.resource_server.converter.user;

import com.myproject.resource_server.model.User;
import com.myproject.resource_server.payload.response.user.UserResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserResponseConverter implements Function<User, UserResponse> {

    @Override
    public UserResponse apply(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setAddress(user.getAddress());
        userResponse.setCity(user.getCity());
        userResponse.setState(user.getState());
        userResponse.setZip(user.getZip());
        userResponse.setPhone(user.getPhone());
        userResponse.setCountry(user.getCountry());
        userResponse.setEmailVerified(user.getEmailVerified());
        return userResponse;
    }

}

