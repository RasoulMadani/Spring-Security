package com.security.springSecurity.controller;

import com.security.springSecurity.dto.UserResponse;
import com.security.springSecurity.model.User;
import com.security.springSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UsersController {
    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok("this is all users");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable int id){
        User user = userService.findById(id);

        UserResponse build = UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
        return ResponseEntity.ok(build);
    }

    @GetMapping("/testAuthService")
    @PostAuthorize("@authService.checkLoadUser(#returnObject)")
    public ResponseEntity<?> testAuthService(){
        return ResponseEntity.ok().build();
    }

}
