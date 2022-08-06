package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.dao.DrinkDAO;
import com.restapp.Restaurant.dao.PizzaDAO;
import com.restapp.Restaurant.dao.SaladDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    PizzaDAO pizza;
    @Autowired
    SaladDAO salad;
    @Autowired
    DrinkDAO drink;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("pizzas", pizza.findAll());
        model.addAttribute("salads", salad.findAll());
        model.addAttribute("drinks", drink.findAll());
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
        return "redirect:/";
    }
}
