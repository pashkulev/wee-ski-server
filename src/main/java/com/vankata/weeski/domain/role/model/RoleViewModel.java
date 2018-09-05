package com.vankata.weeski.domain.role.model;

import com.vankata.weeski.domain.role.enums.Authority;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Role.class, name = "roleViewModel")
public interface RoleViewModel {

    Authority getAuthority();
}
