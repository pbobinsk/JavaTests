package org.java.jwt.rsa;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.file.Path;
import java.security.PrivateKey;
import java.util.Date;

public class JwtRsaGenerator {
    public static String generateJwt(PrivateKey privateKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Data wygaśnięcia (1 godzina)
        long expirationMillis = nowMillis + 3600000;
        Date expiration = new Date(expirationMillis);

        // Tworzenie tokena
        return Jwts.builder()
                .setSubject(subject)                  // "subject" np. User ID
                .setIssuedAt(now)                     // Data wystawienia
                .setExpiration(expiration)            // Data wygaśnięcia
                .signWith(privateKey, SignatureAlgorithm.RS256) // Podpis RSA
                .compact();                           // Finalizacja tokena
    }

    public static void main(String[] args) throws Exception {

        String keysPath = "./.keys/";

        PrivateKey privateKey = RsaKeyLoader.loadPrivateKey(String.valueOf(Path.of(keysPath,"private_key.pem")));
        String jwtToken = generateJwt(privateKey, "User123");
        System.out.println("Generated JWT: " + jwtToken);
    }
}
