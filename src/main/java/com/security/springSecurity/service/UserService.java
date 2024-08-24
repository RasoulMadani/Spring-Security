package com.security.springSecurity.service;

import com.security.springSecurity.config.JwtService;
import com.security.springSecurity.dto.UserRestLoginRequest;
import com.security.springSecurity.dto.UserRestLoginResponse;
import com.security.springSecurity.dto.UserSaveRequest;
import com.security.springSecurity.model.User;
import com.security.springSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("user.not.found"));

    }
    @PostAuthorize("returnObject.username == authentication.principal.username")
    public User findById(int id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("user.not.found"));
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void save(User user){
        userRepository.save(user);
    }

    public UserRestLoginResponse login(UserRestLoginRequest userRestLoginRequest) {

        UserDetails userDetails = loadUserByUsername(userRestLoginRequest.getUsername());
        String token = jwtService.generateToke(userDetails);
        return new UserRestLoginResponse(token);
    }
}
