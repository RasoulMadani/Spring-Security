package com.security.springSecurity.controller;

import com.security.springSecurity.dto.UserSaveRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
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
        System.out.println(userSaveRequest);
        return "redirect:/login";
    }

}
