package com.vankata.weeski.domain.user;

import com.vankata.weeski.domain.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PasswordChangedListener {

    @PrePersist
    @PreUpdate
    public void onPasswordChanged(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }
}
