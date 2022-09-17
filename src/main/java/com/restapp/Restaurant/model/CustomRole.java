package com.restapp.Restaurant.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;

    @ManyToMany(mappedBy = "userRoles", fetch = FetchType.EAGER)
    private Set<CustomUser> roleUsers;

    @Override
    public String getAuthority() {
        return "ROLE_" + roleName;
    }
}
