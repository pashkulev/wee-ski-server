package com.vankata.weeski.domain.role;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = Role.class, name = "roleViewModel")
public interface RoleViewModel {

    Authority getAuthority();
}
