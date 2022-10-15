package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Category;
import com.restapp.Restaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String showCategories(Model model){
        List<Category> categories = categoryService.getAll();
        if (categories.isEmpty())
            return "redirect:/admin/categories/categories-add";
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/admin/categories/categories-add")
    public String getAdd(){
        return "categories-add";
    }

    @GetMapping("/admin/categories/{categoryId}/categories-update")
    public String getUpdate(@PathVariable Integer categoryId, Model model){
        try{
            Category reqCategory = Category.builder().categoryId(categoryId).build();
            Category respCategory = categoryService.getById(reqCategory);
            model.addAttribute("category", respCategory);
            return "categories-update";
        }catch (NoSuchElementException e){
            return "redirect:/admin/categories?success=false";
        }
    }

    @GetMapping("/admin/categories/{categoryId}/categories-delete")
    public String getDelete(@PathVariable Integer categoryId){
        Category reqCategory = Category.builder().categoryId(categoryId).build();
        boolean success = categoryService.delete(reqCategory);
        return "redirect:/admin/categories?success="+success;
    }

    @PostMapping("/admin/categories/categories-add")
    public String postAdd(@RequestParam Map<String, String> params){
        String categoryName = params.get("name");
        Category reqCategory = Category.builder().categoryName(categoryName).build();
        boolean success = categoryService.add(reqCategory);
        return "redirect:/admin/categories?success="+success;
    }

    @PostMapping("/admin/categories/categories-update")
    public String postUpdate(@RequestParam Map<String, String> params){
        Integer categoryId = Integer.parseInt(params.get("id"));
        String categoryName = params.get("name");
        Category reqCategory = Category.builder().categoryId(categoryId).categoryName(categoryName).build();
        boolean success = categoryService.update(reqCategory);
        return "redirect:/admin/categories?success="+success;
    }
}
