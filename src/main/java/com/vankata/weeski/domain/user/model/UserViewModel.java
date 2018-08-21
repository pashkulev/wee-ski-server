package com.vankata.weeski.domain.user.model;

import com.vankata.weeski.domain.role.Role;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;
import java.util.List;

@Projection(types = User.class, name = "userViewModel")
public interface UserViewModel {

    String getFirstName();

    String getLastName();

    String getEmail();

    String getCountry();

    String getEnabled();

    Instant getCreatedAt();

    Instant getUpdatedAt();

    List<Role> getRoles();

}
