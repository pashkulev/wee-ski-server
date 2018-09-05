package com.vankata.weeski.service;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.role.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findByAuthorities(Authority[] authorities);
}
