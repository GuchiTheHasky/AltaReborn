package com.alta.crypto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.crypto.Cipher;
import static org.junit.jupiter.api.Assertions.*;


class CryptographerTest {

    @Test
    @DisplayName("Test Cryptographer, check the encrypted value differs from the original, decrypted value identical to the original")
    public void testCryptographerEncryptDecrypt() throws Exception {
        String ORIGINAL = "textForEncryption";
        String textEncrypted = Cryptographer.crypt(Cipher.ENCRYPT_MODE, ORIGINAL);
        assertNotEquals(textEncrypted, ORIGINAL);
        String textDecrypted = Cryptographer.crypt(Cipher.DECRYPT_MODE, textEncrypted);
        assertEquals(textDecrypted, ORIGINAL);
    }
}