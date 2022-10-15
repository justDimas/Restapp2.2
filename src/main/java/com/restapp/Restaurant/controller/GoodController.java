package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Category;
import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.model.Ingredient;
import com.restapp.Restaurant.model.Property;
import com.restapp.Restaurant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class GoodController {
    @Autowired
    GoodService goodService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    IngredientService ingredientService;
    @Autowired
    PropertyService propertyService;

    @GetMapping("/admin/goods")
    public String getShow(@RequestAttribute(required = false) Boolean success, Model model){
        List<Good> goods = goodService.getAll();
        if(goods.isEmpty())
            return "redirect:/admin/goods/goods-add";
        model.addAttribute("goods", goods);
        return "goods";
    }

    @GetMapping("/admin/goods/goods-add")
    public String getAdd(Model model){
        List<Ingredient> ingredients = ingredientService.getAll();
        List<Property> properties = propertyService.getAll();
        List<Category> categories = categoryService.getAll();

        if(categories.isEmpty())
            return "redirect:/admin/categories/categories-add";
        if(ingredients.isEmpty())
            return "redirect:/admin/ingredients/ingredients-add";

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("properties", properties);
        model.addAttribute("categories", categories);
        return "goods-add";
    }

    @GetMapping("/admin/goods/{goodId}/goods-update")
    public String getUpdate(@PathVariable Integer goodId, Model model){
        Good good;
        try{
            good = goodService.getById(Good.builder().goodId(goodId).build());
        }catch (NoSuchElementException e) {
            return "redirect:/admin/goods?success=false";
        }
        List<Category> categories = categoryService.getAll();
        List<Ingredient> ingredients = ingredientService.getAll();
        List<Property> properties = propertyService.getAll();
        if(categories.isEmpty()) {
            return "redirect:/admin/categories/categories-add";
        }
        if(ingredients.isEmpty()) {
            return "redirect:/admin/ingredients/ingredients-add";
        }
        model.addAttribute("good", good);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("properties", properties);
        model.addAttribute("categories", categories);
        return "goods-update";
    }

    @PostMapping("/admin/goods/goods-add")
    public String postAdd(@RequestParam Map<String,String> params,
                          @RequestParam(required = false) Collection<Integer> properties,
                          @RequestParam Collection<Integer> ingredients)
    {
        boolean success;
        String goodName = params.get("name");
        Double goodPrice = Double.parseDouble(params.get("price"));
        String goodDescription = params.get("description");
        String goodImage = params.get("image");
        Category goodCategory = Category.builder().categoryId(Integer.parseInt(params.get("category"))).build();
        Set<Ingredient> goodIngredients = ingredients.stream()
                .map(id->Ingredient.builder().ingredientId(id).build())
                .collect(Collectors.toSet());
        Set<Property> goodProperties = properties.stream()
                .map(id->Property.builder().propertyId(id).build())
                .collect(Collectors.toSet());

        Good good = Good.builder()
                .goodName(goodName)
                .goodPrice(goodPrice)
                .goodDescription(goodDescription)
                .goodImage(goodImage)
                .goodCategory(goodCategory)
                .goodIngredients(goodIngredients)
                .goodProperties(goodProperties)
                .build();
        success = goodService.add(good);
        return "redirect:/admin/goods" + "?success=" + success;
    }

    @PostMapping("/admin/goods/goods-update")
    public String postUpdate(@RequestParam Map<String,String> params,
                             @RequestParam Collection<Integer> properties,
                             @RequestParam Collection<Integer> ingredients)
    {
        boolean success;
        Integer goodId = Integer.parseInt(params.get("id"));
        String goodName = params.get("name");
        Double goodPrice = Double.parseDouble(params.get("price"));
        String goodDescription = params.get("description");
        String goodImage = params.get("image");
        Category goodCategory = Category.builder().categoryId(Integer.parseInt(params.get("category"))).build();
        Set<Ingredient> goodIngredients = ingredients.stream()
                .map(id->Ingredient.builder().ingredientId(id).build())
                .collect(Collectors.toSet());
        Set<Property> goodProperties = properties.stream()
                .map(id->Property.builder().propertyId(id).build())
                .collect(Collectors.toSet());

        Good good = Good.builder()
                .goodId(goodId)
                .goodName(goodName)
                .goodPrice(goodPrice)
                .goodDescription(goodDescription)
                .goodImage(goodImage)
                .goodCategory(goodCategory)
                .goodIngredients(goodIngredients)
                .goodProperties(goodProperties)
                .build();

        success = goodService.update(good);
        return "redirect:/admin/goods" + "?success=" + success;
    }

    @GetMapping("/admin/goods/{goodId}/goods-delete")
    public String getDelete(@PathVariable Integer goodId){
        boolean success = true;
        try {
            goodService.delete(Good.builder().goodId(goodId).build());
        }catch (IllegalArgumentException e){
            success = false;
        }
        return "redirect:/admin/goods" + "?success=" + success;
    }
}