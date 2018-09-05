package com.vankata.weeski.service;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.payload.ApiResponse;

public interface UserService {

    User findById(String id);

    User findByEmail(String email);

    ApiResponse setUserAuthorities(String userId, Authority[] authorities);

}
