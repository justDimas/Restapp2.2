package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.PropertyDAO;
import com.restapp.Restaurant.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
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
        boolean matchesName = respProperty.getPropertyName().matches("^[А-Яа-я0-9]{2,32}$");
        boolean matchesImage = respProperty.getPropertyImage().matches("^[А-Яа-яA-Za-z0-9\\s\\_\\-\\,\\.]{1,32}\\.[A-Za-z0-9]{1,8}$");
        if(!matchesName || !matchesImage)
            return false;
        propertyDAO.save(respProperty);
        return true;
    }

    @Override
    public boolean delete(Property respProperty) {
        boolean exists = propertyDAO.existsById(respProperty.getPropertyId());
        if(!exists)
            return false;
        propertyDAO.deleteById(respProperty.getPropertyId());
        return true;
    }

    @Override
    public boolean update(Property respProperty) {
        boolean matchesName = respProperty.getPropertyName().matches("^[А-Яа-я0-9]{2,32}$");
        boolean matchesImage = respProperty.getPropertyImage().matches("^[А-Яа-яA-Za-z0-9\\s\\_\\-\\,\\.]{1,32}\\.[A-Za-z0-9]{1,8}$");
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
