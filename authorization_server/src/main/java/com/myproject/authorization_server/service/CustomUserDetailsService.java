package com.myproject.authorization_server.service;

import com.myproject.authorization_server.model.CustomUserDetails;
import com.myproject.authorization_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Invalid username or password.")
        );
        return CustomUserDetails.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
