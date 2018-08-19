package com.vankata.weeski.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = RoleViewModel.class)
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByAuthority(Authority authority);
}
