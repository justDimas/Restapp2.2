package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.CustomRoleDAO;
import com.restapp.Restaurant.model.CustomRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomRoleService {
    @Autowired
    private CustomRoleDAO roleDAO;

    public List<CustomRole> getAll(){
        return roleDAO.findAll();
    }

    public CustomRole getById(CustomRole reqRole){
        return roleDAO.findById(reqRole.getRoleId()).orElseThrow();
    }
}
