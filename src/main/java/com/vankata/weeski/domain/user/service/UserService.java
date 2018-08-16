package com.vankata.weeski.domain.user.service;

import com.vankata.weeski.domain.user.exception.EmailExistsException;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.domain.user.model.UserRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService extends UserDetailsService {

    User register(UserRegisterModel user, MultipartFile imageFile)
            throws EmailExistsException, IOException;
}
