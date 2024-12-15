package org.java.jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtGenerator {

    private static final String SECRET_KEY = "TwójBardzoSekretnyKlucz123451234567890"; // Klucz tajny

    public static String generateJwt(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Ustawienie daty wygaśnięcia (np. 1 godzina)
        long expirationMillis = nowMillis + 3600000; // 1 godzina = 3600000 ms
        Date expiration = new Date(expirationMillis);

        // Tworzenie tokena
        return Jwts.builder()
                .setSubject(subject)                   // Ustawienie "subject"
                .setIssuedAt(now)                      // Ustawienie daty utworzenia
                .setExpiration(expiration)             // Data wygaśnięcia
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Podpisanie kluczem i algorytmem
                .compact();                            // Finalizacja tokena
    }

    public static void main(String[] args) {
        // Generowanie tokena
        String jwtToken = generateJwt("User123"); // "subject" = User123
        System.out.println("Generated JWT: " + jwtToken);
    }
}
