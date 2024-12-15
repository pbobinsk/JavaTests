package org.java.jwt.rsa;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class TestRSA {

    public static void main(String[] args) throws Exception{

        // Ścieżki do plików PEM
        String privateKeyPath = "./.keys/private_key_pkcs8.pem";
        String publicKeyPath = "./.keys/public_key_x509.pem";

        // Wczytanie kluczy
        PrivateKey privateKey = LoadRSAKeys.loadPrivateKey(privateKeyPath);
        PublicKey publicKey = LoadRSAKeys.loadPublicKey(publicKeyPath);

        // Wyświetlenie wczytanych kluczy
        System.out.println("Klucz prywatny: " + privateKey);
        System.out.println("Klucz publiczny: " + publicKey);

        byte[] data = "Wiadomość do podpisania".getBytes();
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        byte[] signedData = signature.sign();
        System.out.println("Podpis: " + Base64.getEncoder().encodeToString(signedData));

        signature.initVerify(publicKey);
        signature.update(data);
        boolean isValid = signature.verify(signedData);
        System.out.println("Czy podpis jest poprawny? " + isValid);
    }


}
