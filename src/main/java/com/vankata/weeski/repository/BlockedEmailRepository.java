package com.vankata.weeski.repository;

import com.vankata.weeski.domain.blockedEmail.model.BlockedEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockedEmailRepository extends JpaRepository<BlockedEmail, Long> {

    Optional<BlockedEmail> findByEmail(String email);
}
