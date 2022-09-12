package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.config.CustomUserDetailsService;
import com.restapp.Restaurant.model.CustomRole;
import com.restapp.Restaurant.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    CustomUserDetailsService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam Map<String, String> params, Model model){
        String userName = params.get("userName");
        String userPassword = params.get("userPassword");
        if (userService.saveUser(new CustomUser(userName, userPassword, new CustomRole(2)))){
            return "redirect:/";
        }else{
            model.addAttribute("error", true);
            return "redirect:/registration";
        }
    }
}
