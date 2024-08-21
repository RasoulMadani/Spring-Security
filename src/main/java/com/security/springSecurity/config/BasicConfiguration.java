package com.security.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicConfiguration  {
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails users = User.withUsername("user").password("password").roles("USER").build();

        UserDetails admin = User.withUsername("admin").password("password").roles("ADMIN","USER").build();
        return new InMemoryUserDetailsManager(users,admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(request->
                request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
