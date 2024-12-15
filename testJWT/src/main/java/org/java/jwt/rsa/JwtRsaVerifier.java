package org.java.jwt.rsa;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.security.PublicKey;

public class JwtRsaVerifier {
    public static void verifyJwt(PublicKey publicKey, String jwtToken) {
        try {
            // Weryfikacja tokena
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey) // Klucz publiczny do weryfikacji
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            // Wyświetlenie danych z tokena
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issued At: " + claims.getIssuedAt());
            System.out.println("Expiration: " + claims.getExpiration());
        } catch (JwtException e) {
            System.out.println("Nieprawidłowy token: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        PublicKey publicKey = RsaKeyLoader.loadPublicKey("./.keys/public_key_x509.pem");
        String jwtToken = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJVc2VyMTIzIiwiaWF0IjoxNzM0Mjg3ODIyLCJleHAiOjE3MzQyOTE0MjJ9.SIdkR9RB1BCuCtak-aG9kk4TvphWi99b1QgLkknj4ePMKQMaD4tEJF1zT4KauHAP_jZ2PJ9RBlOZ9EuZjK5s82MBtm5DIt7xzKD83Co_cXY0ZoJU21GwlboXlbQt4usUCZDLOOHt3eCNzofh0MTRlJy5XiTA_R61RCByGnfwSQMmumUsmMjTuZhxivbr240hHxbcLtanFTwBbsOCHZUZbCtopRKRP3BNhO1AwdD6Yu8BqfJRRuHfTipcMpsR4jvH3-yUcdLOwuIUkEKCwT2XwMnHYXBk-_OGjJhSG2jcLv89C78VYavSmm2bRD_OsdHVSZicK2MG6g-4RUNmomWFjw"; // Wygenerowany token
        verifyJwt(publicKey, jwtToken);
    }
}

