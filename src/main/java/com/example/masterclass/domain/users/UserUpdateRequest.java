package com.example.masterclass.domain.users;

/**
 * Wofür ist <EntityName>UpdateRequest da?
 * - Stellt die Daten dar, die ein Endnutzer übergeben kann, um einen User zu aktualisieren
 */
public class UserUpdateRequest {
    String name;
    String description;

    public UserUpdateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
