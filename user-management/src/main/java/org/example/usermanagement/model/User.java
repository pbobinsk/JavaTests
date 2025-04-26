package org.example.usermanagement.model;


import jakarta.persistence.*;

@Entity
@Table(name = "users")  // Zmieniamy nazwę tabeli na "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Konstruktor bezargumentowy (wymagany przez JPA)
    public User() {}

    // Konstruktor z argumentami (użyteczny w przypadku tworzenia nowych obiektów)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Gettery i settery
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
