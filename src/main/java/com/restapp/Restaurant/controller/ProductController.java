package com.restapp.Restaurant.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ProductController {
    @GetMapping("/products")
    public String registration(Model model){
        return "users";
    }

    @PostMapping("/products")
    public String registration(){
        return "redirect:/users";
    }
}
