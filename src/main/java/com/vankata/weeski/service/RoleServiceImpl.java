package com.vankata.weeski.service;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.role.model.Role;
import com.vankata.weeski.repository.RoleRepository;
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
