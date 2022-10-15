package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.GoodDAO;
import com.restapp.Restaurant.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class GoodService implements MyService<Good> {
    @Autowired
    private GoodDAO goodDAO;

    @Override
    public Good getById(Good respGood) throws NoSuchElementException {
        return goodDAO.findById(respGood.getGoodId()).orElseThrow();
    }

    @Override
    public List<Good> getAll() {
        return goodDAO.findAll();
    }

    @Override
    public boolean add(Good respGood) {
        boolean matchesName = respGood.isValidName();
        boolean matchesPrice = respGood.isValidPrice();
        boolean matchesDescription = respGood.isValidDescription();
        boolean matchesImage = respGood.isValidImage();
        if(!matchesName || !matchesPrice || !matchesDescription || ! matchesImage)
            return false;

        goodDAO.save(respGood);
        return true;
    }

    @Override
    public boolean delete(Good respGood) {
        try{
            goodDAO.deleteById(respGood.getGoodId());
            return true;
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public boolean update(Good respGood) {
        boolean matchesName = respGood.isValidName();
        boolean matchesPrice = respGood.isValidPrice();
        boolean matchesDescription = respGood.isValidDescription();
        boolean matchesImage = respGood.isValidImage();
        if(!matchesName || !matchesPrice || !matchesDescription || ! matchesImage)
            return false;

        Optional<Good> optionalGood = goodDAO.findById(respGood.getGoodId());
        if(optionalGood.isEmpty())
            return false;
        Good good = optionalGood.get();

        boolean equalsName = Objects.equals(respGood.getGoodName(), good.getGoodName());
        boolean equalsPrice = Objects.equals(respGood.getGoodPrice(), good.getGoodPrice());
        boolean equalsDescription = Objects.equals(respGood.getGoodDescription(), good.getGoodDescription());;
        boolean equalsImage = Objects.equals(respGood.getGoodImage(), good.getGoodImage());

        if(!equalsName)
            good.setGoodName(respGood.getGoodName());
        if(!equalsPrice)
            good.setGoodPrice(respGood.getGoodPrice());
        if(!equalsDescription)
            good.setGoodDescription(respGood.getGoodDescription());
        if(!equalsImage)
            good.setGoodImage(respGood.getGoodImage());

        good.setGoodCategory(respGood.getGoodCategory());
        good.setGoodIngredients(respGood.getGoodIngredients());
        good.setGoodProperties(respGood.getGoodProperties());

        goodDAO.save(good);
        return true;
    }
}
