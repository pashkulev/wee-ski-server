package com.vankata.weeski.domain.user.model;

import com.vankata.weeski.domain.role.Role;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(types = User.class, name = "userViewModel")
public interface UserViewModel {

    String getFirstName();

    String getLastName();

    String getEmail();

    String getCountry();

    String getEnabled();

    Set<Role> getRoles();

}
