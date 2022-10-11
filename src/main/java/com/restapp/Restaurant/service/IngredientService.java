package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.IngredientDAO;
import com.restapp.Restaurant.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientService implements MyService<Ingredient> {
    @Autowired
    private IngredientDAO ingredientDAO;

    @Override
    public Ingredient getById(Ingredient respIngredient) throws NoSuchElementException {
        return ingredientDAO.findById(respIngredient.getIngredientId()).orElseThrow();
    }

    @Override
    public List<Ingredient> getAll(){
        return ingredientDAO.findAll();
    }

    @Override
    public boolean add(Ingredient respIngredient) {
        boolean matchesName = respIngredient.getIngredientName().matches("^[А-Яа-я0-9]{2,32}$");
        boolean matchesWeight = (respIngredient.getIngredientWeight()>=0 && respIngredient.getIngredientWeight()<1000);
        if (!matchesName || !matchesWeight)
            return false;

        ingredientDAO.save(respIngredient);
        return true;
    }

    @Override
    public boolean delete(Ingredient respIngredient) {
        boolean exists = ingredientDAO.existsById(respIngredient.getIngredientId());
        if(!exists)
            return false;
        ingredientDAO.deleteById(respIngredient.getIngredientId());
        return true;
    }

    @Override
    public boolean update(Ingredient respIngredient) {
        boolean matchesName = respIngredient.getIngredientName().matches("^[А-Яа-я0-9]{2,32}$");
        boolean matchesWeight = (respIngredient.getIngredientWeight()>=0 && respIngredient.getIngredientWeight()<1000);
        if (!matchesName || !matchesWeight)
            return false;

        Optional<Ingredient> optionalIngredient = ingredientDAO.findById(respIngredient.getIngredientId());
        if(optionalIngredient.isEmpty())
            return false;
        Ingredient ingredient = optionalIngredient.get();

        boolean equalsName = Objects.equals(ingredient.getIngredientName(), respIngredient.getIngredientName());
        boolean equalsWeight = Objects.equals(ingredient.getIngredientWeight(), respIngredient.getIngredientWeight());
        if(!equalsName)
            ingredient.setIngredientName(respIngredient.getIngredientName());
        if(!equalsWeight)
            ingredient.setIngredientWeight(respIngredient.getIngredientWeight());

        ingredient.setIngredientUnit(respIngredient.getIngredientUnit());
        ingredientDAO.save(ingredient);
        return true;
    }
}
