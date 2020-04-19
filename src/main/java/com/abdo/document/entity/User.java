package com.abdo.document.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "username")
    private Set<Authority> authorities = new HashSet<>();

}
