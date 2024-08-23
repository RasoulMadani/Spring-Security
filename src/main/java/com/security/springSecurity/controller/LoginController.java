package com.security.springSecurity.controller;

import com.security.springSecurity.dto.UserSaveRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute UserSaveRequest userSaveRequest){
        System.out.println(userSaveRequest);
        return "redirect:/login";
    }

}
