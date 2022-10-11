package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.service.IngredientService;
import com.restapp.Restaurant.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class IngredientController {
    @Autowired
    IngredientService ingredientService;
    @Autowired
    UnitService unitService;

    @GetMapping("/admin/ingredients")
    public String getIngredients(Model model){
        model.addAttribute("ingredients", ingredientService.getAll());
        model.addAttribute("units", unitService.getAll());
        return "ingredients";
    }

    @PostMapping("/admin/ingredients")
    public String postIngredients(){
        return "redirect:ingredients";
    }

    @DeleteMapping("/admin/ingredients")
    public String deleteIngredients(){
        return "redirect:ingredients";
    }

    @PutMapping("/admin/ingredients")
    public String putIngredients(){
        return "redirect:ingredients";
    }
}