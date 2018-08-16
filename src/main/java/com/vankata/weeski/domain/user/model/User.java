package com.vankata.weeski.domain.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vankata.weeski.domain.role.Role;
import com.vankata.weeski.domain.user.Gender;
import com.vankata.weeski.domain.user.PasswordChangedListener;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Data
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
public class User implements UserDetails {

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

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;

    @Transient
    private boolean isAccountNonExpired;

    @Transient
    private boolean isAccountNonLocked;

    @Transient
    private boolean isCredentialsNonExpired;

    @Transient
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
        this.setAccountNonExpired(true);
        this.setAccountNonLocked(true);
        this.setCredentialsNonExpired(true);
        this.setEnabled(true);
        this.setFriends(new HashSet<>());
        this.setRoles(new HashSet<>());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority().name()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}