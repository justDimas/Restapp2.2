package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.SaladDAO;
import com.restapp.Restaurant.model.Good;
import com.restapp.Restaurant.model.Salad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaladService {
    @Autowired
    private SaladDAO saladDAO;

    public List<Salad> getAll(){
        return saladDAO.findAll();
    }

    public boolean add(Salad salad){
        saladDAO.save(salad);
        return true;
    }

    public boolean update(Salad newSalad){
        Optional<Salad> optionalSalad = saladDAO.findById(newSalad.getSaladId());
        if(optionalSalad.isEmpty())
            return false;
        Salad currentSalad = optionalSalad.get();
        Good newGood = Good.builder()
                .goodId(currentSalad.getGood().getGoodId())
                .goodName(newSalad.getGood().getGoodName())
                .goodPrice(newSalad.getGood().getGoodPrice())
                .build();
        currentSalad.setGood(newGood);
        currentSalad.setGood(newSalad.getGood());
        currentSalad.setIsVegetarian(newSalad.getIsVegetarian());
        currentSalad.setIsWarm(newSalad.getIsWarm());
        if(!newSalad.getSaladImg().isBlank())
            currentSalad.setSaladImg(newSalad.getSaladImg());
        saladDAO.save(currentSalad);
        return true;
    }

    public boolean delete(Salad salad){
        boolean ifExist = saladDAO.existsById(salad.getSaladId());
        if(ifExist)
            saladDAO.deleteById(salad.getSaladId());
        return ifExist;
    }
}
