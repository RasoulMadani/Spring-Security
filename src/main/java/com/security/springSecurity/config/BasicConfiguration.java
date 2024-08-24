package com.security.springSecurity.config;

import com.security.springSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class BasicConfiguration  {
    private final UserService userService;

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails users = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER").build();
//
//        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("password")).roles("ADMIN","USER").build();
//        return new InMemoryUserDetailsManager(users,admin);
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http.csrf().disable().authorizeHttpRequests(request->
                request.requestMatchers(HttpMethod.GET,"/","/index/products","/index/**","/login/register")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST,"/login/register")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST,"/rest/v1")
                        .permitAll()

                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form->form.loginPage("/login").permitAll())
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder (){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
