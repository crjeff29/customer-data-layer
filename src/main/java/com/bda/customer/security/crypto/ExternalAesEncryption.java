package com.bda.customer.security.crypto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

public class ExternalAesEncryption {
    private static final Logger log = LoggerFactory.getLogger(ExternalAesEncryption.class);
    private SecretKeySpec secretKey;
    private IvParameterSpec ivParameterSpec;
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public ExternalAesEncryption(CommonSecurityEncryptionControl ecd) {
        try {
            if (ecd.getEncriptionKey() != null && ecd.getEncriptionKey().length() != 0 && !ecd.getEncriptionKey().isEmpty()) {
                if (ecd.getEncriptionIv() != null && ecd.getEncriptionIv().length() != 0 && !ecd.getEncriptionIv().isEmpty()) {
                    byte[] encriptionKeyByte = ecd.getEncriptionKey().getBytes(StandardCharsets.UTF_8);
                    byte[] encriptionIvByte = ecd.getEncriptionIv().getBytes(StandardCharsets.UTF_8);
                    if (ecd.getEncriptionKey().length() != 0 & ecd.getEncriptionKey().length() < 16) {
                        encriptionKeyByte = Arrays.copyOfRange(encriptionKeyByte, 0, 16);
                    }

                    if (ecd.getEncriptionKey().length() > 16 & ecd.getEncriptionKey().length() < 32 || ecd.getEncriptionKey().length() > 32) {
                        encriptionKeyByte = Arrays.copyOfRange(encriptionKeyByte, 0, 32);
                    }

                    if (ecd.getEncriptionIv().length() != 0 & (ecd.getEncriptionIv().length() < 16 ? true : ecd.getEncriptionIv().length() > 16)) {
                        encriptionIvByte = Arrays.copyOfRange(encriptionIvByte, 0, 16);
                    }

                    this.secretKey = new SecretKeySpec(encriptionKeyByte, ecd.getEncriptionCipher());
                    this.ivParameterSpec = new IvParameterSpec(encriptionIvByte);
                    this.encryptCipher = Cipher.getInstance(ecd.getEncriptionInstance());
                    this.encryptCipher.init(1, this.secretKey, this.ivParameterSpec);
                    this.decryptCipher = Cipher.getInstance(ecd.getEncriptionInstance());
                    this.decryptCipher.init(2, this.secretKey, this.ivParameterSpec);
                } else {
                    throw new Exception("Cannot encrypt because the initialization vector is null or empty");
                }
            } else {
                throw new Exception("Cannot encrypt because the encryption key is null or empty");
            }
        } catch (Exception var4) {
            log.error("Error creating the constructor: " + var4.getMessage());
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
            if (strToDecrypt == null) {
                throw new Exception("Cannot decrypt a null string");
            }

            if (strToDecrypt.length() > 0 && !strToDecrypt.isEmpty()) {
                return new String(this.decryptCipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            }

            return null;
        } catch (BadPaddingException | IllegalBlockSizeException var3) {
            log.error("Error while decrypting 1 : " + var3.getMessage());
        } catch (Exception var4) {
            log.error("Error while decrypting 2 : " + var4.getMessage());
        }

        return null;
    }

    public void checkForUnlimitedCryptoPolicies() throws NoSuchAlgorithmException {
        log.info("Checking for unlimited crypto policies");
        log.info("Restricted cryptography: " + this.checkRestrictedCryptography() + " | Notice: 'false' means unlimited policies");
        log.info("Security properties: " + Security.getProperty("crypto.policy"));
        int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
        log.info("Max AES key length = " + maxKeyLen);
    }

    public boolean checkRestrictedCryptography() {
        try {
            return Cipher.getMaxAllowedKeyLength("AES/CBC/PKCS5Padding") < Integer.MAX_VALUE;
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalStateException("The transform \"AES/CBC/PKCS5Padding\" is not available (the availability of this algorithm is mandatory for Java SE implementations)", var2);
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

    public ExternalAesEncryption(final SecretKeySpec secretKey, final IvParameterSpec ivParameterSpec, final Cipher encryptCipher, final Cipher decryptCipher) {
        this.secretKey = secretKey;
        this.ivParameterSpec = ivParameterSpec;
        this.encryptCipher = encryptCipher;
        this.decryptCipher = decryptCipher;
    }

    public ExternalAesEncryption() {
    }
}
