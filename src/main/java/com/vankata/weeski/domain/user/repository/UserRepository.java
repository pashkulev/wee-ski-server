package com.vankata.weeski.domain.user.repository;

import com.vankata.weeski.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    List<User> findByIdIn(List<String> userIds);

    Boolean existsByEmail(String email);
}
