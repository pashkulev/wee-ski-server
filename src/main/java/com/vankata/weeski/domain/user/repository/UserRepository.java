package com.vankata.weeski.domain.user.repository;

import com.vankata.weeski.domain.user.model.User;
import com.vankata.weeski.domain.user.model.UserViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(excerptProjection = UserViewModel.class)
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    List<User> findByIdIn(List<String> userIds);

    Boolean existsByEmail(String email);
}
