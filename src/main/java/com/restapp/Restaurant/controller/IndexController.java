package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.dao.DrinkDAO;
import com.restapp.Restaurant.dao.PizzaDAO;
import com.restapp.Restaurant.dao.SaladDAO;
import com.restapp.Restaurant.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    @Autowired
    PizzaDAO pizza;
    @Autowired
    SaladDAO salad;
    @Autowired
    DrinkDAO drink;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal CustomUser user){
        model.addAttribute("pizzas", pizza.findAll());
        model.addAttribute("salads", salad.findAll());
        model.addAttribute("drinks", drink.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/")
    public String index(){
        return "redirect:/";
    }
}
