package org.java.jwt.rsa;

import java.security.*;
import java.nio.file.*;
import java.io.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaKeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        String keysPath = "./.keys/";

        // Zapis klucza prywatnego
        Files.write(Path.of(keysPath,"private_key.pem"), keyPair.getPrivate().getEncoded());

        // Zapis klucza publicznego
        Files.write(Path.of(keysPath,"public_key.pem"), keyPair.getPublic().getEncoded());

        System.out.println("Klucze zostały wygenerowane!");

        // Pobranie klucza prywatnego i publicznego
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Zapis klucza prywatnego w formacie PKCS#8
        PKCS8EncodedKeySpec pkcs8Spec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
        try (FileOutputStream fos = new FileOutputStream(String.valueOf(Path.of(keysPath,"private_key_pkcs8.pem")))) {
            fos.write(encodeToPEM("PRIVATE KEY", pkcs8Spec.getEncoded()));
        }

        // Zapis klucza publicznego w formacie X.509 (PEM)
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(publicKey.getEncoded());
        try (FileOutputStream fos = new FileOutputStream(String.valueOf(Path.of(keysPath,"public_key_x509.pem")))) {
            fos.write(encodeToPEM("PUBLIC KEY", x509Spec.getEncoded()));
        }

        System.out.println("Klucze zostały wygenerowane i zapisane w formacie PKCS#8.");


    }
    // Metoda do zapisu klucza w formacie PEM
    private static byte[] encodeToPEM(String type, byte[] keyBytes) {
        StringBuilder pem = new StringBuilder();
        pem.append("-----BEGIN ").append(type).append("-----\n");
        pem.append(java.util.Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(keyBytes));
        pem.append("\n-----END ").append(type).append("-----\n");
        return pem.toString().getBytes();
    }
}
