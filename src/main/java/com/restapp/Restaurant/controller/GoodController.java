package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.dao.DrinkDAO;
import com.restapp.Restaurant.dao.PizzaDAO;
import com.restapp.Restaurant.dao.SaladDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GoodController {
    @Autowired
    PizzaDAO pizza;
    @Autowired
    SaladDAO salad;
    @Autowired
    DrinkDAO drink;

    @GetMapping("/good")
    public String good(Model model){
        model.addAttribute("pizzas", pizza.findAll());
        model.addAttribute("salads", salad.findAll());
        model.addAttribute("drinks", drink.findAll());
        return "good";
    }
    @PostMapping("/good")
    public String good(){
        return "redirect:/good";
    }
}