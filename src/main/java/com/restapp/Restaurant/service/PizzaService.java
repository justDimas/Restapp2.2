package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.PizzaDAO;
import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaDAO pizzaDAO;

    public List<Pizza> getAll(){
        return pizzaDAO.findAll();
    }

    public boolean add(Pizza pizza){
        pizzaDAO.save(pizza);
        return true;
    }

    public boolean update(Pizza newPizza){
        Optional<Pizza> optionalPizza = pizzaDAO.findById(newPizza.getPizzaId());
        if(optionalPizza.isEmpty())
            return false;
        Pizza currentPizza = optionalPizza.get();
        Good newGood = Good.builder()
                .goodId(currentPizza.getGood().getGoodId())
                .goodName(newPizza.getGood().getGoodName())
                .goodPrice(newPizza.getGood().getGoodPrice())
                .goodDescription(newPizza.getGood().getGoodDescription())
                .build();
        currentPizza.setGood(newGood);
        currentPizza.setIsSpicy(newPizza.getIsSpicy());
        currentPizza.setIsVegetarian(newPizza.getIsVegetarian());
        if(!newPizza.getPizzaImg().isBlank())
            currentPizza.setPizzaImg(newPizza.getPizzaImg());
        pizzaDAO.save(currentPizza);
        return true;
    }

    public boolean delete(Pizza pizza){
        boolean ifExist = pizzaDAO.existsById(pizza.getPizzaId());
        if(ifExist)
            pizzaDAO.deleteById(pizza.getPizzaId());
        return ifExist;
    }
}
