package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Category;
import com.restapp.Restaurant.model.CustomUser;
import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.service.CategoryService;
import com.restapp.Restaurant.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Autowired
    GoodService goodService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String getShow(Model model, @AuthenticationPrincipal CustomUser user){
        if(user!=null) {
            boolean isAdmin = user.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            model.addAttribute("user", user);
            model.addAttribute("isAdmin", isAdmin);
        }

        Collection<Good> goods = goodService.getAll();

        Collection<Category> sortCategories = goods.stream()
                .map(Good::getGoodCategory)
                .distinct()
                .sorted(Comparator.comparing(Category::getCategoryName))
                .toList();

        Map<String, List<Good>> goodsByCategory = sortCategories.stream()
                .collect(Collectors.toMap(Category::getCategoryName, category -> goods.stream()
                        .filter(good->Objects.equals(category,good.getGoodCategory())).toList()));
        model.addAttribute("goodsByCategory", goodsByCategory);
        return "index";
    }
}
