# User Management - Spring Boot + Thymeleaf + H2

## 📦 Wymagania
- Java 17+
- IntelliJ IDEA (zalecane)
- Maven

## ▶️ Uruchomienie
1. Otwórz projekt w IntelliJ IDEA jako projekt Maven.
2. Uruchom klasę `UserManagementApplication`.
3. Aplikacja będzie dostępna pod `http://localhost:8080/users`
4. Konsola H2: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:usersdb`)

## ✍️ Funkcjonalności
- Lista użytkowników
- Dodawanie, edycja, usuwanie
- Baza danych H2 in-memory


#!/bin/bash
./mvnw spring-boot:run
