package com.restapp.Restaurant.config;

import com.restapp.Restaurant.dao.CustomUserDAO;
import com.restapp.Restaurant.model.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomUserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        CustomUser requiredUser = userDAO.findByUserName(userName);
        if (requiredUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }
        return User.builder()
                .username(requiredUser.getUserName())
                .password(requiredUser.getUserPassword())
                .roles(requiredUser.getUserRole().toString())
                .build();
    }
}
