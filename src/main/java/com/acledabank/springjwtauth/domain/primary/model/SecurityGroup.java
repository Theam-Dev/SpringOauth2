package com.acledabank.springjwtauth.domain.primary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "security_groups")
public class SecurityGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "securityGroups")
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "security_groups_authorities",
            joinColumns = @JoinColumn(name = "security_group_id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id"))
    private List<Authority> authorities;

}
