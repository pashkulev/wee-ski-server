package com.vankata.weeski.domain.user.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = User.class, name = "userViewModel")
public interface UserViewModel {

    String getFirstName();

    String getLastName();

    String getEmail();

}
