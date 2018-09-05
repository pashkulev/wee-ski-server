package com.vankata.weeski.domain.social.comment.model;

import com.vankata.weeski.domain.user.model.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true)
    private String id;

    @Column(name = "content", columnDefinition = "TEXT NOT NULL")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(nullable = false)
    private String entityId;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

}
