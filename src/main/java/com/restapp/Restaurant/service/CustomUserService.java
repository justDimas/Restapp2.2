package com.restapp.Restaurant.service;

import com.restapp.Restaurant.dao.CustomUserDAO;
import com.restapp.Restaurant.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService, MyService<CustomUser> {
    @Autowired
    private CustomUserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userDAO.findByUserName(userName)
                .orElseThrow(()->new UsernameNotFoundException("Unknown user: " + userName));
    }

    @Override
    public CustomUser getById(CustomUser respUser) throws NoSuchElementException {
        return userDAO.findById(respUser.getUserId()).orElseThrow();
    }

    @Override
    public List<CustomUser> getAll(){
        return userDAO.findAll();
    }

    @Override
    public boolean add(CustomUser respUser){
        boolean matchesLogin = respUser.isValidUsername();
        boolean passwordIsValid = respUser.isValidPassword();
        if(!matchesLogin || !passwordIsValid)
            return false;

        boolean exists = userDAO.existsByUserName(respUser.getUsername());
        if(exists)
            return false;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodePassword = encoder.encode(respUser.getPassword());
        respUser.setUserPassword(encodePassword);
        userDAO.save(respUser);
        return true;
    }

    @Override
    public boolean delete(CustomUser respUser) {
        try{
            userDAO.deleteById(respUser.getUserId());
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public boolean update(CustomUser respUser) {
        boolean matchesLogin = respUser.isValidUsername();
        boolean matchesPassword = respUser.isValidPassword();
        if(!matchesLogin || !matchesPassword) 
            return false;

        Optional<CustomUser> optionalUser = userDAO.findById(respUser.getUserId());
        if(optionalUser.isEmpty())
            return false;
        CustomUser user = optionalUser.get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        boolean equalsPassword = encoder.matches(respUser.getUserPassword(), user.getPassword());
        boolean equalsLogin = Objects.equals(user.getUsername(), respUser.getUsername());
        if(!equalsPassword) 
            user.setUserPassword(encoder.encode(respUser.getUserPassword()));
        if(!equalsLogin)
            user.setUserName(respUser.getUsername());
        
        user.setUserRoles(respUser.getUserRoles());
        userDAO.save(user);
        return true;
    }
}
