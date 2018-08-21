package com.vankata.weeski.domain.blockedEmail;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blocked_emails")
public class BlockedEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    public BlockedEmail(String email) {
        this.email = email;
    }
}
