package com.security.springSecurity.controller;

import com.security.springSecurity.config.SecurityHelper;
import com.security.springSecurity.dto.UserResponse;
import com.security.springSecurity.model.User;
import com.security.springSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private  final UserService userService;
    @GetMapping
    public String init(){
        return "index";
    }
    @GetMapping("/userDetails")
    public String userDetails(Model model){
        model.addAttribute("user", SecurityHelper.getCurrentUser());
        return "userDetails";
    }

    @GetMapping("/userList")
    public String usersPage(Model model){
        List<User> users = userService.findAll();
        List<UserResponse> userResponses = users.stream().map(user -> UserResponse.builder()
                .role(user.getRole())
                .id(user.getId())
                .username(user.getUsername())
                .build()).toList();
        model.addAttribute("userResponse",userResponses);
        return "users";
    }
}
