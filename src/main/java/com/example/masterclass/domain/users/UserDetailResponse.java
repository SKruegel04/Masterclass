package com.example.masterclass.domain.users;

/**
 * Wofür ist <EntityName>DetailResponse da?
 * - Stellt die Ausgabe eines Users an den Endnutzer dar, wenn ein _einzelner_ User abgerufen wird
 */
public class UserDetailResponse {
    Long id;
    String name;
    String description;

    public UserDetailResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
