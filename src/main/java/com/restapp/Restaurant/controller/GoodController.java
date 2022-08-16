package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.dao.DrinkDAO;
import com.restapp.Restaurant.dao.GoodDAO;
import com.restapp.Restaurant.dao.PizzaDAO;
import com.restapp.Restaurant.dao.SaladDAO;
import com.restapp.Restaurant.model.Drink;
import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.model.Pizza;
import com.restapp.Restaurant.model.Salad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GoodController {
    @Autowired
    PizzaDAO pizza;
    @Autowired
    SaladDAO salad;
    @Autowired
    DrinkDAO drink;
    @Autowired
    GoodDAO good;

    @GetMapping("/good")
    public String good(Model model){
        model.addAttribute("pizzas", pizza.findAll());
        model.addAttribute("salads", salad.findAll());
        model.addAttribute("drinks", drink.findAll());
        return "good";
    }
    @PostMapping("/good")
    public String good(@RequestParam Map<String,String> params){
        switch (params.get("form")) {
            case "pizzaAddForm" -> addPizza(params);
            case "pizzaUpdateForm" -> updatePizza(params);
            case "pizzaDeleteForm" -> deletePizza(params);
            case "saladAddForm" -> addSalad(params);
            case "saladUpdateForm" -> updateSalad(params);
            case "saladDeleteForm" -> deleteSalad(params);
            case "drinkAddForm" -> addDrink(params);
            case "drinkUpdateForm" -> updateDrink(params);
            case "drinkDeleteForm" -> deleteDrink(params);
        }
        return "redirect:/good";
    }

    public void addDrink(Map<String,String> params){
        String drinkName = params.get("drinkName");
        Double drinkPrice = Double.parseDouble(params.get("drinkPrice"));
        Boolean drinkIsAlcohol = (params.get("drinkIsAlcohol") != null);
        Boolean drinkIsWarm = (params.get("drinkIsWarm") != null);
        Boolean drinkIsGazed = (params.get("drinkIsGazed") != null);
        Boolean drinkHasCaffeine = (params.get("drinkHasCaffeine") != null);
        String drinkImage = params.get("drinkImage");
        Good requestGood = new Good(drinkName, drinkPrice);
        Drink requestDrink = new Drink(requestGood, drinkIsAlcohol, drinkIsWarm,
                                       drinkIsGazed, drinkHasCaffeine, drinkImage);
        good.save(requestGood);
        drink.save(requestDrink);
    }
    public void updateDrink(Map<String,String> params){

    }
    public void deleteDrink(Map<String,String> params){
        Integer drinkId = Integer.parseInt(params.get("drinkId"));
        drink.deleteById(drinkId);
    }

    public void addPizza(Map<String,String> params){
        String pizzaName = params.get("pizzaName");
        Double pizzaPrice = Double.parseDouble(params.get("pizzaPrice"));
        Boolean pizzaIsVegetarian = (params.get("pizzaIsVegetarian") != null);
        Boolean pizzaIsSpicy = (params.get("pizzaIsSpicy") != null);
        String pizzaImage = params.get("pizzaImage");
        Good requestGood = new Good(pizzaName, pizzaPrice);
        Pizza requestPizza = new Pizza(requestGood, pizzaIsVegetarian, pizzaIsSpicy, pizzaImage);
        good.save(requestGood);
        pizza.save(requestPizza);
    }
    public void updatePizza(Map<String,String> params){

    }
    public void deletePizza(Map<String,String> params){
        Integer pizzaId = Integer.parseInt(params.get("pizzaId"));
        pizza.deleteById(pizzaId);
    }

    public void addSalad(Map<String,String> params){
        String saladName = params.get("saladName");
        Double saladPrice = Double.parseDouble(params.get("saladPrice"));
        Boolean saladIsVegetarian = (params.get("saladIsVegetarian") != null);
        Boolean saladIsWarm = (params.get("saladIsWarm") != null);
        String saladImage = params.get("saladImage");
        Good requestGood = new Good(saladName, saladPrice);
        Salad requestSalad = new Salad(requestGood, saladIsVegetarian, saladIsWarm, saladImage);
        good.save(requestGood);
        salad.save(requestSalad);
    }
    public void updateSalad(Map<String,String> params){

    }
    public void deleteSalad(Map<String,String> params){
        Integer saladId = Integer.parseInt(params.get("saladId"));
        salad.deleteById(saladId);
    }

}