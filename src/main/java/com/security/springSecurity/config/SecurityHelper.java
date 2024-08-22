package com.security.springSecurity.config;

import com.security.springSecurity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {
    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
