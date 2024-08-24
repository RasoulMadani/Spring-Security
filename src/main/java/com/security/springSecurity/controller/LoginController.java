package com.security.springSecurity.controller;

import com.security.springSecurity.dto.UserSaveRequest;
import com.security.springSecurity.enums.Role;
import com.security.springSecurity.model.User;
import com.security.springSecurity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(ModelMap modelMap) {
        modelMap.put("userSaveRequest",new UserSaveRequest("1","1"));
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(
            @ModelAttribute @Valid UserSaveRequest userSaveRequest
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.save(convertUserSaveRequest(userSaveRequest));


        return "redirect:/login";
    }
    private User convertUserSaveRequest(UserSaveRequest userSaveRequest){
        return User.builder()
                .username(userSaveRequest.username())
                .password(passwordEncoder.encode(userSaveRequest.password()))
                .enabled(Boolean.TRUE)
                .role(Arrays.asList(Role.USER))
                .build();
    }

}
