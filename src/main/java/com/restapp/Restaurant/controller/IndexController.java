package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.model.Pizza;
import com.restapp.Restaurant.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    GoodDAO good;
    @Autowired
    PizzaDAO pizza;
    @Autowired
    SaladDAO salad;
    @Autowired
    DrinkDAO drink;
    @GetMapping("/")
    public String index(Model model){
        List<Good> goods = good.findAll();
        List<Pizza> pizzas = pizza.findAll();
        for (Good g : goods) {
            for (Pizza p : pizzas) {
                if(p.getGood().getGoodId().equals(g.getGoodId())){
                    p.setName(g.getGoodName());
                    p.setPrice(g.getGoodPrice());
                }
            }
        }
        model.addAttribute("goods", goods);
        model.addAttribute("pizzas", pizzas);
        return "index";
    }
}
