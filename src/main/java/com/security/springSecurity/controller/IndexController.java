package com.security.springSecurity.controller;

import com.security.springSecurity.config.SecurityHelper;
import com.security.springSecurity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String init(){
        return "index";
    }
    @GetMapping("/userDetails")
    public String userDetails(Model model){
        model.addAttribute("user", SecurityHelper.getCurrentUser());
        return "userDetails";
    }
}
