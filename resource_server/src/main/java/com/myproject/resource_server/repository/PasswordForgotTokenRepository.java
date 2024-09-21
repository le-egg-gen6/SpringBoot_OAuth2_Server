package com.myproject.resource_server.repository;

import com.myproject.resource_server.model.PasswordForgotToken;
import com.myproject.resource_server.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordForgotTokenRepository extends CrudRepository<PasswordForgotToken, Long> {

    Optional<PasswordForgotToken> findByToken(String token);

    Optional<PasswordForgotToken> findByUser(User user);

}
