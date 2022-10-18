package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.CustomUserDAO;
import com.restapp.Restaurant.model.CustomRole;
import com.restapp.Restaurant.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserService implements UserDetailsService{
    @Autowired
    private CustomUserDAO userDAO;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userDAO.findByUserName(userName)
                .orElseThrow(()->new UsernameNotFoundException("Unknown user: " + userName));
    }

    public CustomUser getById(CustomUser reqUser) throws NoSuchElementException {
        return userDAO.findById(reqUser.getUserId()).orElseThrow();
    }

    public List<CustomUser> getAll(){
        return userDAO.findAll();
    }

    public boolean add(CustomUser reqUser){
        boolean matchesLogin = reqUser.isValidUsername();
        boolean passwordIsValid = reqUser.isValidPassword();
        if(!matchesLogin || !passwordIsValid)
            return false;

        boolean exists = userDAO.existsByUserName(reqUser.getUsername());
        if(exists)
            return false;

        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodePassword = encoder.encode(reqUser.getPassword());
        reqUser.setUserPassword(encodePassword);
        userDAO.save(reqUser);
        return true;
    }

    public boolean delete(CustomUser reqUser) {
        try{
            userDAO.deleteById(reqUser.getUserId());
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public boolean updatePassword(CustomUser reqUser) {
        boolean matchesPassword = reqUser.isValidPassword();
        if(!matchesPassword)
            return false;

        Optional<CustomUser> optionalUser = userDAO.findById(reqUser.getUserId());
        if(optionalUser.isEmpty())
            return false;
        CustomUser user = optionalUser.get();

        PasswordEncoder encoder = new BCryptPasswordEncoder(12);
        boolean equalsPassword = encoder.matches(reqUser.getUserPassword(), user.getPassword());
        if(!equalsPassword)
            user.setUserPassword(encoder.encode(reqUser.getUserPassword()));
        userDAO.save(user);
        return true;
    }

    public boolean updateUser(CustomUser reqUser) {
        boolean matchesLogin = reqUser.isValidUsername();
        if(!matchesLogin)
            return false;

        Optional<CustomUser> optionalUser = userDAO.findById(reqUser.getUserId());
        if(optionalUser.isEmpty())
            return false;
        CustomUser user = optionalUser.get();

        boolean equalsLogin = Objects.equals(user.getUsername(), reqUser.getUsername());
        if(!equalsLogin)
            user.setUserName(reqUser.getUsername());

        boolean equalsRoles = reqUser.getUserRoles().size() == user.getUserRoles().size();
        if(!equalsRoles)
            user.setUserRoles(reqUser.getUserRoles());
        userDAO.save(user);
        return true;
    }
}
