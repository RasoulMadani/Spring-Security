package com.security.springSecurity.repository;

import com.security.springSecurity.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Integer> {
}
