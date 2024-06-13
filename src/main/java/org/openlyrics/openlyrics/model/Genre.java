package org.openlyrics.openlyrics.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="genre")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="genre_id")
    private String id;

    @Column(name="name")
    private String name;

    @Lob
    @Column(name="description")
    private String description;

    @ManyToMany
    private Set<Band> bands;
}
