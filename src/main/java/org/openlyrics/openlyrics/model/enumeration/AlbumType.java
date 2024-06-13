package org.openlyrics.openlyrics.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlbumType {
    ALBUM("Album"),
    MIXTAPE("Mixtape"),
    EP("EP"),
    SINGLE("Single"),
    BOOTLEG("Bootleg"),
    LIVE("Live");

    public String formattedType;
}
