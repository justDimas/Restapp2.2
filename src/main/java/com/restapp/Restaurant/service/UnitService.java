package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.UnitDAO;
import com.restapp.Restaurant.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UnitService{
    @Autowired
    private UnitDAO unitDAO;

    public Unit getById(Unit respUnit) throws NoSuchElementException {
        return unitDAO.findById(respUnit.getUnitId()).orElseThrow();
    }
    
    public List<Unit> getAll() {
        return unitDAO.findAll();
    }
    
    public boolean add(Unit respUnit) {
        boolean matchesName = respUnit.isValidName();
        if(!matchesName)
            return false;
        unitDAO.save(respUnit);
        return true;
    }
    
    public boolean delete(Unit respUnit) {
        try{
            unitDAO.deleteById(respUnit.getUnitId());
            return true;
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }

    public boolean update(Unit respUnit) {
        boolean matchesName = respUnit.isValidName();
        if(!matchesName)
            return false;

        Optional<Unit> optionalUnit = unitDAO.findById(respUnit.getUnitId());
        if(optionalUnit.isEmpty())
            return false;
        Unit unit = optionalUnit.get();

        boolean equalsName = Objects.equals(unit.getUnitName(), respUnit.getUnitName());
        if(!equalsName)
            unit.setUnitName(respUnit.getUnitName());
        unitDAO.save(respUnit);
        return true;
    }
}
