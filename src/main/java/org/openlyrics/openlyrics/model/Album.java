package org.openlyrics.openlyrics.model;

import java.time.LocalDate;
import java.util.Set;

import org.openlyrics.openlyrics.model.enumeration.AlbumType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="album")
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="album_id")
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

    @ManyToOne
    @JoinColumn(name="band_uuid")
    private Band band;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private AlbumType type;

    @OneToMany(mappedBy = "album")
    private Set<Music> musics;
}
