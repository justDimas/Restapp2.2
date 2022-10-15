package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.service.CustomUserService;
import com.restapp.Restaurant.model.CustomRole;
import com.restapp.Restaurant.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

@Controller
public class RegistrationController {
    @Autowired
    CustomUserService userService;

    @GetMapping("/registration")
    public String getRegistration(Model model, @RequestParam(required = false) Boolean error){
        if(error!=null)
            model.addAttribute("error", error);
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@RequestParam Map<String, String> params){
        String userName = params.get("userName");
        String userPassword = params.get("userPassword");
        CustomUser user = CustomUser.builder()
                .userName(userName)
                .userPassword(userPassword)
                .userRoles(Set.of(CustomRole.builder().roleId(7).build()))
                .build();
        if (userService.add(user)){
            return "redirect:";
        }else{
            return "redirect:registration?error=true";
        }
    }
}
