package org.java.jwt.rsa;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class LoadRSAKeys {
    public static void main(String[] args) throws Exception {
        // Ścieżki do plików PEM
        String privateKeyPath = "./.keys/private_key_pkcs8.pem";
        String publicKeyPath = "./.keys/public_key_x509.pem";

        // Wczytanie kluczy
        PrivateKey privateKey = loadPrivateKey(privateKeyPath);
        PublicKey publicKey = loadPublicKey(publicKeyPath);

        // Wyświetlenie wczytanych kluczy
        System.out.println("Klucz prywatny: " + privateKey);
        System.out.println("Klucz publiczny: " + publicKey);
    }

    // Metoda do wczytania klucza prywatnego z pliku PEM
    public static PrivateKey loadPrivateKey(String filePath) throws Exception {
        String keyPEM = new String(Files.readAllBytes(new File(filePath).toPath()));
        String privateKeyPEM = keyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", ""); // Usunięcie białych znaków

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // Metoda do wczytania klucza publicznego z pliku PEM
    public static PublicKey loadPublicKey(String filePath) throws Exception {
        String keyPEM = new String(Files.readAllBytes(new File(filePath).toPath()));
        String publicKeyPEM = keyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", ""); // Usunięcie białych znaków

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}

