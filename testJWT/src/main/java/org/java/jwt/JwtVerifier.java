package org.java.jwt;

import io.jsonwebtoken.*;


public class JwtVerifier {

    private static final String SECRET_KEY = "TwójBardzoSekretnyKlucz123451234567890"; // Użyj co najmniej 256-bitowego klucza.


    public static void verifyJwt(String jwtToken) {
        try {
            // Weryfikacja tokena
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes()) // Podaj klucz
                    .build()
                    .parseClaimsJws(jwtToken);

            // Odczytaj payload (claims)
            Claims claims = claimsJws.getBody();
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Expiration: " + claims.getExpiration());

        } catch (ExpiredJwtException e) {
            System.out.println("Token wygasł: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Token jest nieprawidłowy: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Podpis tokena jest nieprawidłowy: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Weryfikacja tokena nie powiodła się: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Przykładowy token JWT
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyMTIzIiwiaWF0IjoxNzM0MjEwMTA2LCJleHAiOjE3MzQyMTM3MDZ9.yi5bW7gf8KEud_jhwSrz3-0kr1QV3MRMFTrfzZb0MlQ"; // Wstaw tutaj token

        String token1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0MjQyIiwibmFtZSI6Ikplc3NpY2EgVGVtcG9yYWwiLCJuaWNrbmFtZSI6Ikplc3MifQ.zDwxJYej2c5WgmaO6BYk8wb0sGM992gGSxL6BXmoPiw";
        String token2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0MjQyIiwibmFtZSI6Ikplc3NpY2EgVGVtcG9yYWwiLCJuaWNrbmFtZSI6Ikplc3MiLCJleHAiOjE3MzQyMTA2NTJ9.ABk8-2wHYDq5q4r1fxHlPUpotEZ7o-m4Ol7SKIMy1tA";



        verifyJwt(token2);
    }
}
