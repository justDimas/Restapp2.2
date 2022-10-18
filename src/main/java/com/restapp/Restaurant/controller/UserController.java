package com.restapp.Restaurant.controller;

import com.restapp.Restaurant.model.CustomRole;
import com.restapp.Restaurant.model.CustomUser;
import com.restapp.Restaurant.service.CustomRoleService;
import com.restapp.Restaurant.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private CustomUserService userService;
    @Autowired
    private CustomRoleService roleService;

    @GetMapping("/admin/users")
    public String getUsers(Model model){
        List<CustomUser> users = userService.getAll();
        if(users.isEmpty())
            return "redirect:/admin/users/users-add";
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/admin/users/users-add")
    public String getAdd(Model model){
        List<CustomRole> roles = roleService.getAll();
        if(roles.isEmpty())
            return "redirect:/admin/users?success=false";
        model.addAttribute("roles", roles);
        return "users-add";
    }

    @GetMapping("/admin/users/{userId}/users-password")
    public String getPassword(@PathVariable Integer userId, Model model){
        CustomUser reqUser = CustomUser.builder().userId(userId).build();
        CustomUser resUser;
        try{
            resUser = userService.getById(reqUser);
        }catch (NoSuchElementException e){
            return "redirect:/admin/users?success=false";
        }
        model.addAttribute("username" ,resUser.getUsername());
        return "users-password";
    }

    @GetMapping("/admin/users/{userId}/users-update")
    public String getUpdate(@PathVariable Integer userId, Model model){
        CustomUser reqUser = CustomUser.builder().userId(userId).build();
        CustomUser resUser;
        try{
            resUser = userService.getById(reqUser);
        }catch (NoSuchElementException e){
            return "redirect:/admin/users?success=false";
        }
        List<CustomRole> roles = roleService.getAll();
        if(roles.isEmpty())
            return "redirect:/admin/users?success=false";
        model.addAttribute("roles", roles);
        model.addAttribute("user", resUser);
        return "users-update";
    }

    @GetMapping("/admin/users/{userId}/users-delete")
    public String getDelete(@PathVariable Integer userId){
        CustomUser reqUser = CustomUser.builder().userId(userId).build();
        boolean success = userService.delete(reqUser);
        return "redirect:/admin/users?success="+success;
    }

    @PostMapping("/admin/users/{userId}/users-password")
    public String postPassword(@PathVariable Integer userId,
                               @RequestParam String password,
                               @RequestParam String passwordRepeat)
    {
        if(!Objects.equals(password, passwordRepeat))
            return "redirect:/admin/users?success=false";
        CustomUser reqUser = CustomUser.builder()
                .userId(userId).userPassword(password).build();
        boolean success = userService.updatePassword(reqUser);
        return "redirect:/admin/users?success="+success;
    }

    @PostMapping("/admin/users/users-add")
    public String postAdd(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String passwordRepeat,
                          @RequestParam Collection<Integer> roleIds)
    {
        if(!Objects.equals(password, passwordRepeat))
            return "redirect:/admin/users?success=false";
        Set<CustomRole> roles = roleIds.stream()
                .map(id -> CustomRole.builder().roleId(id).build())
                .collect(Collectors.toSet());
        CustomUser reqUser = CustomUser.builder()
                .userName(username)
                .userPassword(password)
                .userRoles(roles)
                .build();
        boolean success = userService.add(reqUser);
        return "redirect:/admin/users?success="+success;
    }

    @PostMapping("/admin/users/{userId}/users-update")
    public String postUpdate(@PathVariable Integer userId,
                             @RequestParam String username,
                             @RequestParam Collection<Integer> roleIds)
    {
        Set<CustomRole> roles = roleIds.stream()
                .map(id -> CustomRole.builder().roleId(id).build())
                .collect(Collectors.toSet());
        CustomUser reqUser = CustomUser.builder()
                .userId(userId)
                .userName(username)
                .userRoles(roles)
                .build();
        boolean success = userService.updateUser(reqUser);
        return "redirect:/admin/users?success="+success;
    }
}
