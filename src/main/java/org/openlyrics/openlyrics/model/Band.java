package org.openlyrics.openlyrics.model;

import java.time.LocalDate;
import java.util.Set;

import org.openlyrics.openlyrics.interfaces.Collaborator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="band")
@Data
public class Band implements Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="band_id")
    private String id;

    @Column(name="creation")
    private LocalDate creation;

    @ManyToMany
    private Set<Genre> genres;

    @Column(name="name")
    private String name;

    @Lob
    @Column(name="description")
    private String description;

    @Lob
    @Column(name="photo")
    private String photo;

    @ManyToMany
    private Set<Artist> participants;

    @OneToMany(mappedBy = "band")
    private Set<Album> albums;
}
