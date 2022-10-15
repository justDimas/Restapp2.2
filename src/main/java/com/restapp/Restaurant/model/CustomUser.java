package com.restapp.Restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "users"),
               inverseJoinColumns = @JoinColumn(name="roles"))
    @ToString.Exclude
    private Set<CustomRole> userRoles;
    @Transient
    @JsonIgnore
    private static final String regexCheckUsername = "^[a-zA-Z0-9]{4,32}$";
    @Transient
    @JsonIgnore
    private static final String regexCheckPassword = "^[a-zA-Z0-9]{4,32}$";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isValidPassword(){
        return userPassword.matches(regexCheckPassword);
    }
    public boolean isValidUsername(){
        return userName.matches(regexCheckUsername);
    }
}
