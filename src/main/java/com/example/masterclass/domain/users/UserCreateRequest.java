package com.example.masterclass.domain.users;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Wofür ist <EntityName>CreateRequest da?
 * - Stellt die Daten dar, die ein Endnutzer übergeben kann, um einen User zu erstellen
 */
public class UserCreateRequest {
    @Length(min = 2)
    @NotBlank
    String name;
    @Length(min = 4)
    @NotBlank
    String description;

    public UserCreateRequest(String name, String description) {
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
