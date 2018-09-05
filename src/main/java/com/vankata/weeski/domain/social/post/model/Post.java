package com.vankata.weeski.domain.social.post.model;

import com.vankata.weeski.domain.user.model.User;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true)
    private String id;

    @Column
    private String title;

    @Column(name = "content", columnDefinition = "TEXT NOT NULL")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String entityId;
}
