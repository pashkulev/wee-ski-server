package com.vankata.weeski.domain.user.service;

import com.vankata.weeski.domain.role.Authority;
import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.payload.ApiResponse;

public interface UserService {

    User findById(String id);

    ApiResponse setUserAuthorities(String userId, Authority[] authorities);

}
