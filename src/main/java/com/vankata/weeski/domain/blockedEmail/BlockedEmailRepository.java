package com.vankata.weeski.domain.blockedEmail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockedEmailRepository extends JpaRepository<BlockedEmail, Long> {

    Optional<BlockedEmail> findByEmail(String email);
}
