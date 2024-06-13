package org.openlyrics.openlyrics.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="music")
@Data
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="music_id")
    private String id;

    @Column(name="creation")
    private LocalDate creation;

    @Column(name="name")
    private String name;

    @Lob
    @Column(name="lyric")
    private String lyric;

    @ManyToOne
    @JoinColumn(name="album_uuid")
    private Album album;
}
