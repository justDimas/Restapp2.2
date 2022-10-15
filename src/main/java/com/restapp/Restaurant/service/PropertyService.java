package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.PropertyDAO;
import com.restapp.Restaurant.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class PropertyService implements MyService<Property> {
    @Autowired
    private PropertyDAO propertyDAO;

    @Override
    public Property getById(Property respProperty) throws NoSuchElementException {
        return propertyDAO.findById(respProperty.getPropertyId()).orElseThrow();
    }

    @Override
    public List<Property> getAll() {
        return propertyDAO.findAll();
    }

    @Override
    public boolean add(Property respProperty) {
        boolean matchesName = respProperty.isValidName();
        boolean matchesImage = respProperty.isValidImage();
        if(!matchesName || !matchesImage)
            return false;
        propertyDAO.save(respProperty);
        return true;
    }

    @Override
    public boolean delete(Property respProperty) {
        try{
            propertyDAO.deleteById(respProperty.getPropertyId());
            return true;
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public boolean update(Property respProperty) {
        boolean matchesName = respProperty.isValidName();
        boolean matchesImage = respProperty.isValidImage();
        if(!matchesName || !matchesImage)
            return false;

        Optional<Property> optionalProperty = propertyDAO.findById(respProperty.getPropertyId());
        if(optionalProperty.isEmpty())
            return false;
        Property property = optionalProperty.get();

        boolean equalsName = Objects.equals(property.getPropertyName(), respProperty.getPropertyName());
        boolean equalsImage = Objects.equals(property.getPropertyImage(),respProperty.getPropertyImage());
        if(!equalsName)
            property.setPropertyName(respProperty.getPropertyName());

        if(!equalsImage)
            property.setPropertyImage(respProperty.getPropertyImage());

        propertyDAO.save(property);
        return true;
    }
}
