package com.alta.crypto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.crypto.Cipher;
import static org.junit.jupiter.api.Assertions.*;


class CryptographerTest {

    @Test
    @DisplayName("Test Cryptographer, check the encrypted value differs from the original, decrypted value identical to the original")
    public void testCryptographerEncryptDecrypt() throws Exception {
        String originalText = "textForEncryption";
        String textEncrypted = Cryptographer.crypt(Cipher.ENCRYPT_MODE, originalText);
        assertNotEquals(textEncrypted, originalText);
        String textDecrypted = Cryptographer.crypt(Cipher.DECRYPT_MODE, textEncrypted);
        assertEquals(textDecrypted, originalText);
    }
}