package org.openlyrics.openlyrics.model;

import org.openlyrics.openlyrics.model.enumeration.RoleName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="role")
public class UserRole {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="role_id")
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private RoleName role;
}
