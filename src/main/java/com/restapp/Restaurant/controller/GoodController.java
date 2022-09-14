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
import java.util.Optional;

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

    @GetMapping("/goods")
    public String good(Model model){
        model.addAttribute("pizzas", pizza.findAll());
        model.addAttribute("salads", salad.findAll());
        model.addAttribute("drinks", drink.findAll());
        return "goods";
    }
    @PostMapping("/goods")
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
        return "redirect:/goods";
    }

    public void addDrink(Map<String,String> params){
        String drinkName = params.get("drinkName");
        Double drinkPrice = Double.parseDouble(params.get("drinkPrice"));
        Boolean drinkIsAlcohol = (params.get("drinkIsAlcohol") != null);
        Boolean drinkIsWarm = (params.get("drinkIsWarm") != null);
        Boolean drinkIsGazed = (params.get("drinkIsGazed") != null);
        Boolean drinkHasCaffeine = (params.get("drinkHasCaffeine") != null);
        String drinkImage = params.get("drinkImage");

        Good requestGood = Good.builder()
                .goodName(drinkName)
                .goodPrice(drinkPrice)
                .build();
        Drink requestDrink = Drink.builder()
                .good(requestGood)
                .isAlcohol(drinkIsAlcohol)
                .isWarm(drinkIsWarm)
                .isGazed(drinkIsGazed)
                .hasCaffeine(drinkHasCaffeine)
                .drinkImg(drinkImage)
                .build();
        good.save(requestGood);
        drink.save(requestDrink);
    }
    public void updateDrink(Map<String,String> params){
        Integer drinkId = Integer.parseInt(params.get("drinkId"));
        String drinkName = params.get("drinkName");
        Double drinkPrice = Double.parseDouble(params.get("drinkPrice"));
        Boolean drinkIsAlcohol = (params.get("drinkIsAlcohol") != null);
        Boolean drinkIsWarm = (params.get("drinkIsWarm") != null);
        Boolean drinkIsGazed = (params.get("drinkIsGazed") != null);
        Boolean drinkHasCaffeine = (params.get("drinkHasCaffeine") != null);
        String drinkImage = params.get("drinkImage");

        Optional<Drink> drinkOptional = drink.findById(drinkId);
        if(drinkOptional.isEmpty()) return;

        Drink requestDrink = drinkOptional.get();
        Good requestGood = requestDrink.getGood();

        requestDrink.setIsAlcohol(drinkIsAlcohol);
        requestDrink.setIsWarm(drinkIsWarm);
        requestDrink.setIsGazed(drinkIsGazed);
        requestDrink.setHasCaffeine(drinkHasCaffeine);
        if(!drinkImage.isEmpty())
            requestDrink.setDrinkImg(drinkImage);
        requestGood.setGoodName(drinkName);
        requestGood.setGoodPrice(drinkPrice);

        drink.save(requestDrink);
        good.save(requestGood);
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

        Good requestGood = Good.builder()
                .goodName(pizzaName)
                .goodPrice(pizzaPrice)
                .build();
        Pizza requestPizza = Pizza.builder()
                .good(requestGood)
                .isVegetarian(pizzaIsVegetarian)
                .isSpicy(pizzaIsSpicy)
                .pizzaImg(pizzaImage)
                .build();

        good.save(requestGood);
        pizza.save(requestPizza);
    }
    public void updatePizza(Map<String,String> params){
        Integer pizzaId = Integer.parseInt(params.get("pizzaId"));
        String pizzaName = params.get("pizzaName");
        Double pizzaPrice = Double.parseDouble(params.get("pizzaPrice"));
        Boolean pizzaIsVegetarian = (params.get("pizzaIsVegetarian") != null);
        Boolean pizzaIsSpicy = (params.get("pizzaIsSpicy") != null);
        String pizzaImage = params.get("pizzaImage");

        Optional<Pizza> pizzaOptional = pizza.findById(pizzaId);
        if(pizzaOptional.isEmpty()) return;

        Pizza requestPizza = pizzaOptional.get();
        Good requestGood = requestPizza.getGood();

        requestPizza.setIsVegetarian(pizzaIsVegetarian);
        requestPizza.setIsSpicy(pizzaIsSpicy);
        if(!pizzaImage.isEmpty())
            requestPizza.setPizzaImg(pizzaImage);
        requestGood.setGoodName(pizzaName);
        requestGood.setGoodPrice(pizzaPrice);

        good.save(requestGood);
        pizza.save(requestPizza);
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

        Good requestGood = Good.builder()
                .goodName(saladName)
                .goodPrice(saladPrice)
                .build();
        Salad requestSalad = Salad.builder()
                .good(requestGood)
                .isVegetarian(saladIsVegetarian)
                .isWarm(saladIsWarm)
                .saladImg(saladImage)
                .build();

        good.save(requestGood);
        salad.save(requestSalad);
    }
    public void updateSalad(Map<String,String> params){
        Integer saladId = Integer.parseInt(params.get("saladId"));
        String saladName = params.get("saladName");
        Double saladPrice = Double.parseDouble(params.get("saladPrice"));
        Boolean saladIsVegetarian = (params.get("saladIsVegetarian") != null);
        Boolean saladIsWarm = (params.get("saladIsWarm") != null);
        String saladImage = params.get("saladImage");

        Optional<Salad> saladOptional = salad.findById(saladId);
        if(saladOptional.isEmpty()) return;

        Salad requestSalad = saladOptional.get();
        Good requestGood = requestSalad.getGood();

        requestSalad.setIsVegetarian(saladIsVegetarian);
        requestSalad.setIsWarm(saladIsWarm);
        if(!saladImage.isEmpty())
            requestSalad.setSaladImg(saladImage);
        requestGood.setGoodName(saladName);
        requestGood.setGoodPrice(saladPrice);

        good.save(requestGood);
        salad.save(requestSalad);
    }
    public void deleteSalad(Map<String,String> params){
        Integer saladId = Integer.parseInt(params.get("saladId"));
        salad.deleteById(saladId);
    }
}