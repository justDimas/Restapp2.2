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
    @Autowired
    PizzaDAO pizza;
    @Autowired
    PizzaSizeDAO pizzaSize;
    @Autowired
    SaladDAO salad;
    @Autowired
    DrinkDAO drink;
    @GetMapping("/")
    public String index(Model model){
        List<Good> goods = good.findAll();
        List<Pizza> pizzas = pizza.findAll();
        List<PizzaSize> pizzaSizes = pizzaSize.findAll();
        for (Good g : goods) {
            for (Pizza p : pizzas) {
                if(p.getGood().getGoodId().equals(g.getGoodId())){
                    p.setName(g.getGoodName());
                    p.setPrice(g.getGoodPrice());
                }
            }
        }
        for (Pizza p : pizzas) {
            switch (p.getPizzaSize().getPizzaSizeName()) {
                case "S" -> {
                    p.setPrice(p.getPrice() * 1);
                    p.setName(p.getName() + " S");
                }
                case "M" -> {
                    p.setPrice(p.getPrice() * 1.5);
                    p.setName(p.getName() + " M");
                }
                case "L" -> {
                    p.setPrice(p.getPrice() * 2);
                    p.setName(p.getName() + " L");
                }
            }
        }
        model.addAttribute("goods", goods);
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("pizzaSizes", pizzaSizes);
        return "index";
    }
}
