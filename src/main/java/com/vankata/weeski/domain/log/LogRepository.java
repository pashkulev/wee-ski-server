package com.vankata.weeski.domain.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
