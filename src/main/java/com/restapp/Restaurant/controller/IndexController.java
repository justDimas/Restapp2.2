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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/")
    public String index(@RequestParam(required = false) String pizzaAdd,
                        @RequestParam(required = false) String pizzaUpdate,
                        @RequestParam(required = false) String pizzaDelete,
                        @RequestParam(required = false) String saladAdd,
                        @RequestParam(required = false) String saladUpdate,
                        @RequestParam(required = false) String saladDelete,
                        @RequestParam(required = false) String drinkAdd,
                        @RequestParam(required = false) String drinkUpdate,
                        @RequestParam(required = false) String drinkDelete,

                        @RequestParam(required = false) Integer pizzaId,
                        @RequestParam(required = false) String pizzaName,
                        @RequestParam(required = false) Double pizzaPrice,
                        @RequestParam(required = false, defaultValue = "false") Boolean pizzaIsSpicy,
                        @RequestParam(required = false, defaultValue = "false") Boolean pizzaIsVegan,
                        @RequestParam(required = false) String pizzaImg,

                        @RequestParam(required = false) Integer saladId,
                        @RequestParam(required = false) String saladName,
                        @RequestParam(required = false) Double saladPrice,
                        @RequestParam(required = false, defaultValue = "false") Boolean saladIsWarm,
                        @RequestParam(required = false, defaultValue = "false") Boolean saladIsVegan,
                        @RequestParam(required = false) String saladImg,

                        @RequestParam(required = false) Integer drinkId,
                        @RequestParam(required = false) String drinkName,
                        @RequestParam(required = false) Double drinkPrice,
                        @RequestParam(required = false, defaultValue = "false") Boolean drinkHasCoff,
                        @RequestParam(required = false, defaultValue = "false") Boolean drinkIsAlc,
                        @RequestParam(required = false, defaultValue = "false") Boolean drinkIsWarm,
                        @RequestParam(required = false, defaultValue = "false") Boolean drinkIsGazed,
                        @RequestParam(required = false) String drinkImg)
    {
        if(pizzaAdd != null)
            addPizza( pizzaName, pizzaPrice, pizzaIsSpicy, pizzaIsVegan, pizzaImg);
        else if(pizzaUpdate != null)
            ;//pizzaUpdate
        else if(pizzaDelete != null)
            pizza.deleteById(pizzaId);
        else if(saladAdd != null)
            addSalad(saladName, saladPrice, saladIsWarm, saladIsVegan, saladImg);
        else if(saladUpdate != null)
            ;//saladUpdate
        else if(saladDelete != null)
            salad.deleteById(saladId);
        else if(drinkAdd != null)
            addDrink(drinkName, drinkPrice, drinkHasCoff, drinkIsAlc, drinkIsWarm, drinkIsGazed, drinkImg);
        else if(drinkUpdate != null)
            ;//drinkUpdate
        else if(drinkDelete != null)
            drink.deleteById(drinkId);
        return "redirect:/";
    }

    private void addPizza(String pizzaName,
                          Double pizzaPrice,
                          Boolean pizzaIsSpicy,
                          Boolean pizzaIsVegan,
                          String pizzaImg)
    {
        System.out.println(pizzaImg);
        Good saved = good.save(new Good(pizzaName, pizzaPrice));
        pizza.save(new Pizza(saved, pizzaIsSpicy, pizzaIsVegan, pizzaImg));
    }

    private void addSalad(String saladName,
                          Double saladPrice,
                          Boolean saladIsWarm,
                          Boolean saladIsVegan,
                          String saladImg)
    {
        Good saved = good.save(new Good(saladName, saladPrice));
        salad.save(new Salad(saved, saladIsWarm, saladIsVegan, saladImg));
    }

    private void addDrink(String drinkName,
                          Double drinkPrice,
                          Boolean hasCoff,
                          Boolean isAlc,
                          Boolean isWarm,
                          Boolean isGazed,
                          String drinkImg)
    {
        Good saved = good.save(new Good(drinkName, drinkPrice));
        drink.save(new Drink(saved, isAlc, isWarm, isGazed, hasCoff, drinkImg));
    }
}
