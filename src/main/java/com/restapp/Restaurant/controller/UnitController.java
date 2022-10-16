package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.Unit;
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
public class UnitController {
    @Autowired
    UnitService unitService;

    @GetMapping("/admin/units")
    public String showUnits(Model model){
        List<Unit> units = unitService.getAll();
        if (units.isEmpty())
            return "redirect:/admin/units/units-add";
        model.addAttribute("units", units);
        return "units";
    }
    @GetMapping("/admin/units/units-add")
    public String getAdd(){
        return "units-add";
    }
    @GetMapping("/admin/units/{unitId}/units-update")
    public String getUpdate(@PathVariable Integer unitId, Model model){
        try{
            Unit reqUnit = Unit.builder().unitId(unitId).build();
            Unit resUnit = unitService.getById(reqUnit);
            model.addAttribute("unit",resUnit);
            return "units-update";
        }catch (NoSuchElementException e){
            return "redirect:/admin/units?success=false";
        }
    }
    @GetMapping("/admin/units/{unitId}/units-delete")
    public String getDelete(@PathVariable Integer unitId){
        Unit reqUnit = Unit.builder().unitId(unitId).build();
        boolean success = unitService.delete(reqUnit);
        return "redirect:/admin/units?success="+success;
    }
    @PostMapping("/admin/units/units-update")
    public String postUpdate(@RequestParam Integer id, @RequestParam String name){
        Unit reqUnit = Unit.builder().unitId(id).unitName(name).build();
        boolean success = unitService.update(reqUnit);
        return "redirect:/admin/units?success="+success;
    }
    @PostMapping("/admin/units/units-add")
    public String postAdd(@RequestParam String name){
        Unit reqUnit = Unit.builder().unitName(name).build();
        boolean success = unitService.add(reqUnit);
        return "redirect:/admin/units?success="+success;
    }
}
