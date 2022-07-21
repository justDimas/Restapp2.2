package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.dao.*;
import com.restapp.Restaurant.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    GoodDAO good;
    PizzaDAO pizza;
    PizzaSizeDAO pizzaSize;
    SaladDAO salad;
    DrinkDAO drink;
    @GetMapping("/")
    public String index(Model model){
        List<Good> goods = good.findAll();
        List<Pizza> pizzas = pizza.findAll();
        List<PizzaSize> pizzaSizes = pizzaSize.findAll();
        List<Salad> salads = salad.findAll();
        List<Drink> drinks = drink.findAll();
        model.addAttribute("goods", goods);
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("pizzaSizes", pizzaSizes);
        model.addAttribute("salads", salads);
        model.addAttribute("drinks", drinks);
        return "index";
    }
}
