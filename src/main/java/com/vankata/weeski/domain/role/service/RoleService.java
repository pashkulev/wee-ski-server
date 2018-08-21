package com.vankata.weeski.domain.role.service;

import com.vankata.weeski.domain.role.Authority;
import com.vankata.weeski.domain.role.Role;

import java.util.List;

public interface RoleService {

    List<Role> findByAuthorities(Authority[] authorities);
}
