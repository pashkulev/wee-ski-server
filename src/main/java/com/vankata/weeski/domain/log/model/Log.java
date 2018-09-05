package com.vankata.weeski.domain.log.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "logs")
@EntityListeners(AuditingEntityListener.class)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "request_method")
    private String requestMethod;

    @Column(nullable = false)
    private String uri;

    @Column(nullable = false, name = "status_code")
    private Integer statusCode;

    @Column(nullable = false)
    private String identity;

    @CreatedDate
    @Column(nullable = false)
    private Instant timestamp;

    public Log(String requestMethod, String uri, Integer statusCode, String identity) {
        this.requestMethod = requestMethod;
        this.uri = uri;
        this.statusCode = statusCode;
        this.identity = identity;
    }
}