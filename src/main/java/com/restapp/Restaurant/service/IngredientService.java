package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.IngredientDAO;
import com.restapp.Restaurant.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientService{
    @Autowired
    private IngredientDAO ingredientDAO;

    public Ingredient getById(Ingredient respIngredient) throws NoSuchElementException {
        return ingredientDAO.findById(respIngredient.getIngredientId()).orElseThrow();
    }
    
    public List<Ingredient> getAll(){
        return ingredientDAO.findAll();
    }
    
    public boolean add(Ingredient respIngredient) {
        boolean matchesName = respIngredient.isValidName();
        boolean matchesWeight = respIngredient.isValidPrice();
        if (!matchesName || !matchesWeight)
            return false;

        ingredientDAO.save(respIngredient);
        return true;
    }
    
    public boolean delete(Ingredient respIngredient) {
        try{
            ingredientDAO.deleteById(respIngredient.getIngredientId());
            return true;
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }
    
    public boolean update(Ingredient respIngredient) {
        boolean matchesName = respIngredient.isValidName();
        boolean matchesWeight = respIngredient.isValidPrice();
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
