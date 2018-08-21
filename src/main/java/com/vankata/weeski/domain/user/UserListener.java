package com.vankata.weeski.domain.user;

import com.vankata.weeski.domain.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.PrePersist;

public class UserListener {

    @PrePersist
    public void onPrePersist(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }
}
