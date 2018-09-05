package com.vankata.weeski.domain.role.model;

import com.vankata.weeski.domain.role.enums.Authority;
import com.vankata.weeski.domain.user.model.User;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(nullable = false)
    private Authority authority;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return this.authority.toString();
    }
}
