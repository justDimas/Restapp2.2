package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Ingredient;
import com.restapp.Restaurant.model.Unit;
import com.restapp.Restaurant.service.IngredientService;
import com.restapp.Restaurant.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class IngredientController {
    @Autowired
    IngredientService ingredientService;
    @Autowired
    UnitService unitService;

    @GetMapping("/admin/ingredients")
    public String getIngredients(Model model){
        List<Ingredient> ingredients = ingredientService.getAll();
        if (ingredients.isEmpty())
            return "redirect:/admin/ingredients/ingredients-add";
        model.addAttribute("ingredients", ingredients);
        return "ingredients";
    }
    @GetMapping("/admin/ingredients/{ingredientId}/ingredients-update")
    public String getUpdate(@PathVariable Integer ingredientId, Model model){
        List<Unit> units = unitService.getAll();
        if(units.isEmpty())
            return "redirect:/admin/units/units-add";
        Ingredient reqIngredient = Ingredient.builder().ingredientId(ingredientId).build();
        Ingredient resIngredient;
        try{
            resIngredient = ingredientService.getById(reqIngredient);
        }catch (NoSuchElementException e){
            return "redirect:/admin/ingredients?success=false";
        }
        model.addAttribute("ingredient", resIngredient);
        model.addAttribute("units", units);
        return "ingredients-update";
    }
    @GetMapping("/admin/ingredients/ingredients-add")
    public String getAdd(Model model){
        List<Unit> units = unitService.getAll();
        if(units.isEmpty())
            return "redirect:/admin/units/units-add";
        model.addAttribute("units", units);
        return "ingredients-add";
    }
    @GetMapping("/admin/ingredients/{ingredientId}/ingredients-delete")
    public String getDelete(@PathVariable Integer ingredientId){
        Ingredient resIngredient = Ingredient.builder().ingredientId(ingredientId).build();
        boolean success = ingredientService.delete(resIngredient);
        return "redirect:/admin/ingredients?success="+success;
    }
    @PostMapping("/admin/ingredients/ingredients-add")
    public String postAdd(@RequestParam String name,
                          @RequestParam Double weight,
                          @RequestParam Integer unitId)
    {
        Unit reqUnit = Unit.builder().unitId(unitId).build();
        Ingredient ingredient = Ingredient.builder()
                .ingredientName(name)
                .ingredientWeight(weight)
                .ingredientUnit(reqUnit).build();
        boolean success = ingredientService.add(ingredient);
        return "redirect:/admin/ingredients?success="+success;
    }
    @PostMapping("/admin/ingredients/ingredients-update")
    public String postUpdate(@RequestParam Integer ingredientId,
                             @RequestParam String name,
                             @RequestParam Double weight,
                             @RequestParam Integer unitId)
    {
        Unit reqUnit = Unit.builder().unitId(unitId).build();
        Ingredient ingredient = Ingredient.builder()
                .ingredientId(ingredientId)
                .ingredientName(name)
                .ingredientWeight(weight)
                .ingredientUnit(reqUnit).build();
        boolean success = ingredientService.update(ingredient);
        return "redirect:/admin/ingredients?success="+success;
    }
}