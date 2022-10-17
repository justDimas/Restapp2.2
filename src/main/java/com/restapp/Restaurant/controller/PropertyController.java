package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Property;
import com.restapp.Restaurant.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping("/admin/properties")
    public String getProperties(Model model){
        List<Property> properties = propertyService.getAll();
        if (properties.isEmpty())
            return "redirect:/admin/properties/properties-add";
        model.addAttribute("properties", properties);
        return "properties";
    }
    @GetMapping("/admin/properties/properties-add")
    public String getAdd(){
        return "properties-add";
    }
    @GetMapping("/admin/properties/{propertyId}/properties-update")
    public String getUpdate(@PathVariable Integer propertyId, Model model){
        Property reqProperty = Property.builder().propertyId(propertyId).build();
        Property resProperty;
        try{
            resProperty = propertyService.getById(reqProperty);
        }catch (NoSuchElementException e){
            return "redirect:/admin/properties?success=false";
        }
        model.addAttribute("property", resProperty);
        return "properties-update";
    }
    @GetMapping("/admin/properties/{propertyId}/properties-delete")
    public String getDelete(@PathVariable Integer propertyId){
        Property reqProperty = Property.builder().propertyId(propertyId).build();
        boolean success = propertyService.delete(reqProperty);
        return "redirect:/admin/properties?success"+success;
    }

    @PostMapping("/admin/properties/{propertyId}/properties-update")
    public String postUpdate(@PathVariable Integer propertyId,
                             @RequestParam String name,
                             @RequestParam String image)
    {
        Property resProperty = Property.builder()
                .propertyId(propertyId)
                .propertyName(name)
                .propertyImage(image)
                .build();
        boolean success = propertyService.update(resProperty);
        return "redirect:/admin/properties?success"+success;
    }
    @PostMapping("/admin/properties/properties-add")
    public String postAdd(@RequestParam String name,
                          @RequestParam String image)
    {
        Property reqProperty = Property.builder()
                .propertyName(name)
                .propertyImage(image)
                .build();
        boolean success = propertyService.add(reqProperty);
        return "redirect:/admin/properties?success"+success;
    }
}
