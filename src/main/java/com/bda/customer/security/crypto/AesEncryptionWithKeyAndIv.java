package com.bda.customer.security.crypto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Base64;

public class AesEncryptionWithKeyAndIv {
    private static final Logger log = LoggerFactory.getLogger(AesEncryptionWithKeyAndIv.class);
    private SecretKeySpec secretKey;
    private IvParameterSpec ivParameterSpec;
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public AesEncryptionWithKeyAndIv(CommonSecurityEncryptionControl ecd) {
        try {
            if (ecd.getEncriptionKey() == null || ecd.getEncriptionKey().length() == 0 || ecd.getEncriptionKey().isEmpty()) {
                throw new Exception("Cannot encrypt because the encryption key is null or empty");
            }

            if (ecd.getEncriptionIv() == null || ecd.getEncriptionIv().length() == 0 || ecd.getEncriptionIv().isEmpty()) {
                throw new Exception("Cannot encrypt because the initialization vector is null or empty");
            }

            this.secretKey = new SecretKeySpec(ecd.getEncriptionKey().getBytes(StandardCharsets.UTF_8), ecd.getEncriptionCipher());
            this.ivParameterSpec = new IvParameterSpec(ecd.getEncriptionIv().getBytes(StandardCharsets.UTF_8));
            this.encryptCipher = Cipher.getInstance(ecd.getEncriptionInstance());
            this.encryptCipher.init(1, this.secretKey, this.ivParameterSpec);
            this.decryptCipher = Cipher.getInstance(ecd.getEncriptionInstance());
            this.decryptCipher.init(2, this.secretKey, this.ivParameterSpec);
        } catch (Exception var3) {
            log.error("Error creating the constructor: " + var3.getMessage());
        }

    }

    public String encrypt(String strToEncrypt) {
        try {
            if (strToEncrypt != null && strToEncrypt.length() > 0 && !strToEncrypt.isEmpty()) {
                return Base64.getEncoder().encodeToString(this.encryptCipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
            }

            throw new Exception("Cannot encrypt a null or empty string");
        } catch (BadPaddingException | IllegalBlockSizeException var3) {
            log.error("Error while encrypting 1: " + var3.getMessage());
        } catch (Exception var4) {
            log.error("Error while encrypting 2: " + var4.getMessage());
        }

        return null;
    }

    public String decrypt(String strToDecrypt) {
        try {
            if (strToDecrypt != null && strToDecrypt.length() > 0 && !strToDecrypt.isEmpty()) {
                return new String(this.decryptCipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            }

            throw new Exception("Cannot decrypt a null or empty string");
        } catch (BadPaddingException | IllegalBlockSizeException var3) {
            log.error("Error while decrypting 1: " + var3.getMessage());
        } catch (Exception var4) {
            log.error("Error while decrypting 2: " + var4.getMessage());
        }

        return null;
    }

    public void CheckForUnlimitedCryptoPolicies() throws NoSuchAlgorithmException {
        log.info("Check for unlimited crypto policies");
        log.info("Restricted cryptography: " + checkRestrictedCryptography() + " | Notice: 'false' means unlimited policies");
        log.info("Security properties: " + Security.getProperty("crypto.policy"));
        int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
        log.info("Max AES key length = " + maxKeyLen);
    }

    public static boolean checkRestrictedCryptography() {
        try {
            return Cipher.getMaxAllowedKeyLength("AES/CBC/PKCS5Padding") < Integer.MAX_VALUE;
        } catch (NoSuchAlgorithmException var1) {
            throw new IllegalStateException("The transform \"AES/CBC/PKCS5Padding\" is not available (the availability of this algorithm is mandatory for Java SE implementations)", var1);
        }
    }

    public String generateKey(Integer keySize) throws NoSuchAlgorithmException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(keySize);
            SecretKey secretKey = keyGenerator.generateKey();
            String secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            return secretKeyString;
        } catch (Exception var5) {
            log.error("Error generating the key " + var5.getMessage());
            return null;
        }
    }
}
