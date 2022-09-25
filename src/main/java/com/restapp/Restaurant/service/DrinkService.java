package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.DrinkDAO;
import com.restapp.Restaurant.model.Drink;
import com.restapp.Restaurant.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {
    @Autowired
    private DrinkDAO drinkDAO;

    public List<Drink> getAll(){
        return drinkDAO.findAll();
    }

    public boolean add(Drink drink){
        drinkDAO.save(drink);
        return true;
    }

    public boolean update(Drink newDrink){
        Optional<Drink> optionalDrink = drinkDAO.findById(newDrink.getDrinkId());
        if(optionalDrink.isEmpty())
            return false;
        Drink currentDrink = optionalDrink.get();
        Good newGood = Good.builder()
                .goodId(currentDrink.getGood().getGoodId())
                .goodName(newDrink.getGood().getGoodName())
                .goodPrice(newDrink.getGood().getGoodPrice())
                .goodDescription(newDrink.getGood().getGoodDescription())
                .build();
        currentDrink.setGood(newGood);
        currentDrink.setHasCaffeine(newDrink.getHasCaffeine());
        currentDrink.setIsAlcohol(newDrink.getIsAlcohol());
        currentDrink.setIsGazed(newDrink.getIsGazed());
        currentDrink.setIsWarm(newDrink.getIsWarm());
        if(!newDrink.getDrinkImg().isBlank())
            currentDrink.setDrinkImg(newDrink.getDrinkImg());
        drinkDAO.save(currentDrink);
        return true;
    }

    public boolean delete(Drink drink){
        boolean exists = drinkDAO.existsById(drink.getDrinkId());
        if(exists)
            drinkDAO.deleteById(drink.getDrinkId());
        return exists;
    }
}
