package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.CategoryDAO;
import com.restapp.Restaurant.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService implements MyService<Category> {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Category getById(Category respCategory) throws NoSuchElementException{
        return categoryDAO.findById(respCategory.getCategoryId()).orElseThrow();
    }

    @Override
    public List<Category> getAll(){
        return categoryDAO.findAll();
    }

    @Override
    public boolean add(Category respCategory) {
        boolean matchesName = respCategory.isValidName();
        if(!matchesName)
            return false;
        categoryDAO.save(respCategory);
        return true;
    }

    @Override
    public boolean delete(Category respCategory) {
        try{
            categoryDAO.deleteById(respCategory.getCategoryId());
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public boolean update(Category respCategory) {
        boolean matchesName = respCategory.isValidName();
        if(!matchesName)
            return false;

        Optional<Category> optionalCategory = categoryDAO.findById(respCategory.getCategoryId());
        if(optionalCategory.isEmpty())
            return false;
        Category category = optionalCategory.get();

        boolean equalsName = Objects.equals(respCategory.getCategoryName(), category.getCategoryName());
        if(!equalsName)
            category.setCategoryName(respCategory.getCategoryName());
        categoryDAO.save(category);
        return true;
    }
}
