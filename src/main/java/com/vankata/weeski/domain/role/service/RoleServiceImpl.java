package com.vankata.weeski.domain.role.service;

import com.vankata.weeski.domain.role.Authority;
import com.vankata.weeski.domain.role.Role;
import com.vankata.weeski.domain.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findByAuthorities(Authority[] authorities) {
        return this.roleRepository.findByAuthorityIn(authorities);
    }
}
