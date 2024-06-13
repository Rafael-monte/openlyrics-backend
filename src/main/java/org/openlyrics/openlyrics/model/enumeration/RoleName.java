package org.openlyrics.openlyrics.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleName {
    ADMIN("Administrator"),
    USER("User"),
    EDITOR("Editor");

    private String formattedName;
}
