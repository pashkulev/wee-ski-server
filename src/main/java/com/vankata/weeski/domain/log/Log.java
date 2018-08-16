package com.vankata.weeski.domain.log;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "logs")
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

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Log(String requestMethod, String uri, Integer statusCode, String identity) {
        this.requestMethod = requestMethod;
        this.uri = uri;
        this.statusCode = statusCode;
        this.identity = identity;
        this.timestamp = LocalDateTime.now();
    }
}