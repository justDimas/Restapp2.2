package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Drink;
import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.model.Pizza;
import com.restapp.Restaurant.dao.*;
import com.restapp.Restaurant.model.Salad;
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
        List<Salad> salads = salad.findAll();
        List<Drink> drinks = drink.findAll();

        for (Pizza p: pizzas) {
            for (Good g : goods) {
                if(p.getGood().getGoodId() == g.getGoodId()){
                    p.setPrice(g.getGoodPrice());
                    p.setName(g.getGoodName());
                    break;
                }
            }
        }

        for (Salad s: salads) {
            for (Good g : goods) {
                if(s.getGood().getGoodId() == g.getGoodId()){
                    s.setPrice(g.getGoodPrice());
                    s.setName(g.getGoodName());
                    break;
                }
            }
        }

        for (Drink d: drinks) {
            for (Good g : goods) {
                if(d.getGood().getGoodId() == g.getGoodId()){
                    d.setPrice(g.getGoodPrice());
                    d.setName(g.getGoodName());
                    break;
                }
            }
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("salads", salads);
        model.addAttribute("drinks", drinks);
        return "index";
    }
}
