package com.security.springSecurity.repository;

import com.security.springSecurity.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    List<Token> findByToken(String token);
}
