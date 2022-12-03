package com.example.masterclass.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Was ist die Aufgabe des <EntityName>Repository?
 * - O/R Mapping Schnittstelle
 * - Erlaubt direkten Zugriff auf O/R-gemappte Entities aus der Datenbank
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
