package com.alta.crypto;

import lombok.Value;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

@Value
public class Cryptographer {
    private static final String KEY = "encryptDecryptAesKey";

     /* Encrypt text: Cryptographer.crypt(Cipher.ENCRYPT_MODE, originalText);
        Decrypt text: Cryptographer.crypt(Cipher.DECRYPT_MODE, textEncrypted); */

    public static String crypt(int mode, String value) throws Exception {
        byte[] valueBytes = value.getBytes(StandardCharsets.ISO_8859_1);
        byte[] keyBytes = KEY.getBytes(StandardCharsets.ISO_8859_1);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        keyBytes = messageDigest.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);

        SecretKeySpec secureKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, secureKey);

        byte[] finalValue = cipher.doFinal(valueBytes);

        return new String(finalValue, StandardCharsets.ISO_8859_1);
    }
}
