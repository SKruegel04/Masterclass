package com.example.masterclass.domain.users;

/**
 * Wofür ist <EntityName>ListResponse da?
 * - Stellt die Ausgabe mehrerer User an den Endnutzer dar, wenn _mehrere_ User abgerufen werden
 */
public class UserListResponse {
    Long id;
    String name;
    String description;

    public UserListResponse(Long id, String name, String description) {
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