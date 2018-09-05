package com.vankata.weeski.domain.user.model;

import com.vankata.weeski.domain.role.model.Role;
import com.vankata.weeski.domain.user.enums.Gender;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Projection(types = User.class, name = "userViewModel")
public interface UserViewModel {

    String getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    LocalDate getBirthDate();

    Gender getGender();

    String getProfilePicture();

    String getCountry();

    String getEnabled();

    Instant getCreatedAt();

    Instant getUpdatedAt();

    List<Role> getRoles();

}
