package com.vankata.weeski.repository;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.role.model.Role;
import com.vankata.weeski.domain.role.model.RoleViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(excerptProjection = RoleViewModel.class)
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByAuthority(Authority authority);

    List<Role> findByAuthorityIn(Authority[] authorities);
}
