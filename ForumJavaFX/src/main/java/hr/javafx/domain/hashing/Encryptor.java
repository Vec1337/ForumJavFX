package hr.javafx.domain.hashing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public static String encryptString(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(plainText.getBytes());

        BigInteger bi = new BigInteger(1, messageDigest);

        return bi.toString(16);
    }

    /*
    public static void main(String[] args) {
        try {
            String password = encryptString("Pass4");

            System.out.println(password);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

    }

     */

}
