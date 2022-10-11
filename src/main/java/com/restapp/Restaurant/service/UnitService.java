package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.UnitDAO;
import com.restapp.Restaurant.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UnitService implements MyService<Unit>{
    @Autowired
    private UnitDAO unitDAO;

    @Override
    public Unit getById(Unit respUnit) throws NoSuchElementException {
        return unitDAO.findById(respUnit.getUnitId()).orElseThrow();
    }

    @Override
    public List<Unit> getAll() {
        return unitDAO.findAll();
    }

    @Override
    public boolean add(Unit respUnit) {
        boolean matchesName = respUnit.getUnitName().matches("^[А-Яа-я0-9]{2,16}$");
        if(!matchesName)
            return false;
        unitDAO.save(respUnit);
        return true;
    }

    @Override
    public boolean delete(Unit respUnit) {
        boolean exists = unitDAO.existsById(respUnit.getUnitId());
        if(!exists)
            return false;
        unitDAO.delete(respUnit);
        return true;
    }

    @Override
    public boolean update(Unit respUnit) {
        boolean matchesName = respUnit.getUnitName().matches("^[А-Яа-я0-9]{2,32}$");
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
