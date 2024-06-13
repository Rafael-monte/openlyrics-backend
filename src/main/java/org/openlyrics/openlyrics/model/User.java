package org.openlyrics.openlyrics.model;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="user_id")
    private String id;

    @Column(name="username", unique = true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="document", unique = true)
    private String document;

    @Column(name="suspended")
    private Boolean isAccountSuspended;

    @Column(name="deleted")
    private Boolean isAccountDeleted;

    @Column(name="suspended_until")
    private LocalDateTime suspendedUntil;

    @Lob
    @Column(name="photo")
    private String photo;

    @Lob
    @Column(name="description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<UserRole> roles;
}
