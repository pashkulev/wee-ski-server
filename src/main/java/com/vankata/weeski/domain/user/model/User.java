package com.vankata.weeski.domain.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vankata.weeski.domain.role.Role;
import com.vankata.weeski.domain.user.Gender;
import com.vankata.weeski.domain.user.PasswordChangedListener;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners({
        PasswordChangedListener.class,
        AuditingEntityListener.class
})
@JsonIgnoreProperties(
        value = {"createdAd", "updatedAt", "friends", "roles"},
        allowGetters = true
)
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private String country;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    @Column(name="created_at",nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at" ,nullable = false)
    private Instant updatedAt;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
        this.setFriends(new HashSet<>());
        this.setRoles(new HashSet<>());
    }
}