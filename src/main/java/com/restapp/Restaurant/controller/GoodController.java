package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Drink;
import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.model.Pizza;
import com.restapp.Restaurant.model.Salad;
import com.restapp.Restaurant.service.DrinkService;
import com.restapp.Restaurant.service.PizzaService;
import com.restapp.Restaurant.service.SaladService;
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
    PizzaService pizza;
    @Autowired
    DrinkService drink;
    @Autowired
    SaladService salad;

    @GetMapping("/admin/goods")
    public String good(Model model){
        model.addAttribute("pizzas", pizza.getAll());
        model.addAttribute("salads", salad.getAll());
        model.addAttribute("drinks", drink.getAll());
        return "goods";
    }
    @PostMapping("/admin/goods")
    public String good(@RequestParam Map<String,String> params){
        boolean success = false;
        switch (params.get("form")) {
            case "pizzaAddForm" ->    success = pizzaAdd(params);
            case "pizzaUpdateForm" -> success = pizzaUpdate(params);
            case "pizzaDeleteForm" -> success = pizzaDelete(params);
            case "saladAddForm" ->    success = saladAdd(params);
            case "saladUpdateForm" -> success = saladUpdate(params);
            case "saladDeleteForm" -> success = saladDelete(params);
            case "drinkAddForm" ->    success = drinkAdd(params);
            case "drinkUpdateForm" -> success = drinkUpdate(params);
            case "drinkDeleteForm" -> success = drinkDelete(params);
        }
        return "redirect:goods" + "?success=" + success;
    }

    public boolean pizzaAdd(Map<String,String> params){
        String pizzaName = params.get("pizzaName");
        Double pizzaPrice = Double.parseDouble(params.get("pizzaPrice"));
        String pizzaDescription = params.get("pizzaDescription");
        Boolean pizzaIsVegetarian = (params.get("pizzaIsVegetarian") != null);
        Boolean pizzaIsSpicy = (params.get("pizzaIsSpicy") != null);
        String pizzaImage = params.get("pizzaImage");

        Good responseGood = Good.builder()
                .goodName(pizzaName)
                .goodPrice(pizzaPrice)
                .goodDescription(pizzaDescription)
                .build();
        Pizza responsePizza = Pizza.builder()
                .good(responseGood)
                .isVegetarian(pizzaIsVegetarian)
                .isSpicy(pizzaIsSpicy)
                .pizzaImg(pizzaImage)
                .build();

        return pizza.add(responsePizza);
    }
    public boolean pizzaUpdate(Map<String,String> params){
        Integer pizzaId = Integer.parseInt(params.get("pizzaId"));
        String pizzaName = params.get("pizzaName");
        Double pizzaPrice = Double.parseDouble(params.get("pizzaPrice"));
        String pizzaDescription = params.get("pizzaDescription");
        Boolean pizzaIsVegetarian = (params.get("pizzaIsVegetarian") != null);
        Boolean pizzaIsSpicy = (params.get("pizzaIsSpicy") != null);
        String pizzaImage = params.get("pizzaImage");

        Good responseGood = Good.builder()
                .goodName(pizzaName)
                .goodPrice(pizzaPrice)
                .goodDescription(pizzaDescription)
                .build();
        Pizza responsePizza = Pizza.builder()
                .pizzaId(pizzaId)
                .good(responseGood)
                .isVegetarian(pizzaIsVegetarian)
                .isSpicy(pizzaIsSpicy)
                .pizzaImg(pizzaImage)
                .build();

        return pizza.update(responsePizza);
    }
    public boolean pizzaDelete(Map<String,String> params){
        Integer pizzaId = Integer.parseInt(params.get("pizzaId"));
        Pizza responsePizza = Pizza.builder().pizzaId(pizzaId).build();
        return pizza.delete(responsePizza);
    }

    public boolean saladAdd(Map<String,String> params){
        String saladName = params.get("saladName");
        Double saladPrice = Double.parseDouble(params.get("saladPrice"));
        String saladDescription = params.get("saladDescription");
        Boolean saladIsVegetarian = (params.get("saladIsVegetarian") != null);
        Boolean saladIsWarm = (params.get("saladIsWarm") != null);
        String saladImage = params.get("saladImage");

        Good responseGood = Good.builder()
                .goodName(saladName)
                .goodPrice(saladPrice)
                .goodDescription(saladDescription)
                .build();
        Salad responseSalad = Salad.builder()
                .good(responseGood)
                .isVegetarian(saladIsVegetarian)
                .isWarm(saladIsWarm)
                .saladImg(saladImage)
                .build();
        return salad.add(responseSalad);
    }
    public boolean saladUpdate(Map<String,String> params){
        Integer saladId = Integer.parseInt(params.get("saladId"));
        String saladName = params.get("saladName");
        Double saladPrice = Double.parseDouble(params.get("saladPrice"));
        String saladDescription = params.get("saladDescription");
        Boolean saladIsVegetarian = (params.get("saladIsVegetarian") != null);
        Boolean saladIsWarm = (params.get("saladIsWarm") != null);
        String saladImage = params.get("saladImage");

        Good responseGood = Good.builder()
                .goodName(saladName)
                .goodPrice(saladPrice)
                .goodDescription(saladDescription)
                .build();
        Salad responseSalad = Salad.builder()
                .saladId(saladId)
                .good(responseGood)
                .isVegetarian(saladIsVegetarian)
                .isWarm(saladIsWarm)
                .saladImg(saladImage)
                .build();

        return salad.update(responseSalad);
    }
    public boolean saladDelete(Map<String,String> params){
        Integer saladId = Integer.parseInt(params.get("saladId"));
        Salad responseSalad = Salad.builder().saladId(saladId).build();
        return salad.delete(responseSalad);
    }

    public boolean drinkAdd(Map<String,String> params){
        String drinkName = params.get("drinkName");
        Double drinkPrice = Double.parseDouble(params.get("drinkPrice"));
        String drinkDescription = params.get("drinkDescription");
        Boolean drinkIsAlcohol = (params.get("drinkIsAlcohol") != null);
        Boolean drinkIsWarm = (params.get("drinkIsWarm") != null);
        Boolean drinkIsGazed = (params.get("drinkIsGazed") != null);
        Boolean drinkHasCaffeine = (params.get("drinkHasCaffeine") != null);
        String drinkImage = params.get("drinkImage");

        Good responseGood = Good.builder()
                .goodName(drinkName)
                .goodPrice(drinkPrice)
                .goodDescription(drinkDescription)
                .build();
        Drink responseDrink = Drink.builder()
                .good(responseGood)
                .isAlcohol(drinkIsAlcohol)
                .isWarm(drinkIsWarm)
                .isGazed(drinkIsGazed)
                .hasCaffeine(drinkHasCaffeine)
                .drinkImg(drinkImage)
                .build();
        return drink.add(responseDrink);
    }
    public boolean drinkUpdate(Map<String,String> params){
        Integer drinkId = Integer.parseInt(params.get("drinkId"));
        String drinkName = params.get("drinkName");
        Double drinkPrice = Double.parseDouble(params.get("drinkPrice"));
        String drinkDescription = params.get("drinkDescription");
        Boolean drinkIsAlcohol = (params.get("drinkIsAlcohol") != null);
        Boolean drinkIsWarm = (params.get("drinkIsWarm") != null);
        Boolean drinkIsGazed = (params.get("drinkIsGazed") != null);
        Boolean drinkHasCaffeine = (params.get("drinkHasCaffeine") != null);
        String drinkImage = params.get("drinkImage");

        Good responseGood = Good.builder()
                .goodName(drinkName)
                .goodPrice(drinkPrice)
                .goodDescription(drinkDescription)
                .build();
        Drink responseDrink = Drink.builder()
                .drinkId(drinkId)
                .good(responseGood)
                .isAlcohol(drinkIsAlcohol)
                .isWarm(drinkIsWarm)
                .isGazed(drinkIsGazed)
                .hasCaffeine(drinkHasCaffeine)
                .drinkImg(drinkImage)
                .build();
        return drink.update(responseDrink);
    }
    public boolean drinkDelete(Map<String,String> params){
        Integer drinkId = Integer.parseInt(params.get("drinkId"));
        Drink responseDrink = Drink.builder().drinkId(drinkId).build();
        return drink.delete(responseDrink);
    }
}