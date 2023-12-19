package com.alta.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class Cryptographer {

     /* Encrypt text: Cryptographer.crypt(Cipher.ENCRYPT_MODE, originalText, key);
        Decrypt text: Cryptographer.crypt(Cipher.DECRYPT_MODE, textEncrypted, key); */

    public static String crypt(int mode, String value, String key) throws Exception {
        byte[] valueBytes = value.getBytes(StandardCharsets.ISO_8859_1);
        byte[] keyBytes = key.getBytes(StandardCharsets.ISO_8859_1);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        keyBytes = messageDigest.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);
        SecretKeySpec secureKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, secureKey);

        return new String(cipher.doFinal(valueBytes), StandardCharsets.ISO_8859_1);
    }

}
