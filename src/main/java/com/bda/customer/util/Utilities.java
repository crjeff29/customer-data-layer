package com.bda.customer.util;

import com.bda.customer.config.CryptoPropertiesConf;
import com.bda.customer.dto.CustomerDTO;
import com.bda.customer.entity.Customer;
import com.bda.customer.security.crypto.CommonSecurityEncryptionControl;
import com.bda.customer.security.crypto.ExternalAesEncryption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class Utilities {

    private final CryptoPropertiesConf cryptoPropertiesConf;

    public CustomerDTO setDTO(Optional<Customer> customer) {
        return customer.map(c -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(c.getId());
            customerDTO.setBusinessPartner(c.getBusinessPartner());
            customerDTO.setName(c.getName());
            customerDTO.setIdDocumentList(c.getIdDocumentList());
            customerDTO.setAddress(c.getAddress());
            customerDTO.setPhone(c.getPhone());
            customerDTO.setIdCustomerStatusList(c.getIdCustomerStatusList());
            customerDTO.setIdCustomerTypeList(c.getIdCustomerTypeList());
            customerDTO.setIdCustomerLevelList(c.getIdCustomerLevelList());
            customerDTO.setIdGroup(c.getIdGroup());
            customerDTO.setEmailAddress(c.getEmailAddress());
            return customerDTO;
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public String base64Encode(String strToEncode) {
        try {
            if (strToEncode != null && !strToEncode.isEmpty()) {
                return Base64.getEncoder().encodeToString(strToEncode.getBytes(StandardCharsets.UTF_8));
            } else {
                throw new Exception("Cannot encode a null or empty string");
            }
        } catch (Exception ex) {
            log.error("Error encoding base64: " + ex.getMessage());
            return null;
        }
    }

    public String base64Decode(String strToDecode) {
        try {
            if (strToDecode != null && !strToDecode.isEmpty()) {
                return new String(Base64.getDecoder().decode(strToDecode.getBytes(StandardCharsets.UTF_8)));
            } else {
                throw new Exception("Cannot decode a null or empty string");
            }
        } catch (Exception ex) {
            log.error("Error decoding base64: " + ex.getMessage());
            return null;
        }
    }

    public String base64EncodeMime(String strToEncode) {
        try {
            if (strToEncode != null && !strToEncode.isEmpty()) {
                return Base64.getMimeEncoder().encodeToString(strToEncode.getBytes(StandardCharsets.UTF_8.name()));
            } else {
                throw new Exception("Cannot encode a null or empty string");
            }
        } catch (Exception ex) {
            log.error("Error encoding base64: " + ex.getMessage());
            return null;
        }
    }

    public String base64EncodeRecursive(String strToEncode) {
        try {
            if (strToEncode != null && !strToEncode.isEmpty()) {
                String firstPass = Base64.getEncoder().encodeToString(strToEncode.getBytes(StandardCharsets.UTF_8));
                return Base64.getEncoder().encodeToString(firstPass.getBytes(StandardCharsets.UTF_8));
            } else {
                throw new Exception("Cannot encode a null or empty string");
            }
        } catch (Exception ex) {
            log.error("Error encoding base64: " + ex.getMessage());
            return null;
        }
    }

    public String base64DecodeRecursive(String strToDecode) {
        try {
            if (strToDecode != null && !strToDecode.isEmpty()) {
                String firstPass = new String(Base64.getDecoder().decode(strToDecode.getBytes(StandardCharsets.UTF_8)));
                return new String(Base64.getDecoder().decode(firstPass.getBytes(StandardCharsets.UTF_8)));
            } else {
                throw new Exception("Cannot decode a null or empty string");
            }
        } catch (Exception ex) {
            log.error("Error decoding base64: " + ex.getMessage());
            return null;
        }
    }

    public String base64EncodeTripleRecursive(String strToEncode) {
        try {
            if (strToEncode != null && !strToEncode.isEmpty()) {
                String firstPass = Base64.getEncoder().encodeToString(strToEncode.getBytes(StandardCharsets.UTF_8));
                String secondPass = Base64.getEncoder().encodeToString(firstPass.getBytes(StandardCharsets.UTF_8));
                return Base64.getEncoder().encodeToString(secondPass.getBytes(StandardCharsets.UTF_8));
            } else {
                throw new Exception("Cannot encode a null or empty string");
            }
        } catch (Exception ex) {
            log.error("Error encoding base64: " + ex.getMessage());
            return null;
        }
    }

    public String base64DecodeTripleRecursive(String strToDecode) {
        try {
            if (strToDecode != null && !strToDecode.isEmpty()) {
                String firstPass = new String(Base64.getDecoder().decode(strToDecode.getBytes(StandardCharsets.UTF_8)));
                String secondPass = new String(Base64.getDecoder().decode(firstPass.getBytes(StandardCharsets.UTF_8)));
                return new String(Base64.getDecoder().decode(secondPass.getBytes(StandardCharsets.UTF_8)));
            } else {
                throw new Exception("Cannot decode a null or empty string");
            }
        } catch (Exception ex) {
            log.error("Error decoding base64: " + ex.getMessage());
            return null;
        }
    }

    public String encodeAESString(String srtToEncrypt) {
        ExternalAesEncryption encryption = new ExternalAesEncryption(
                CommonSecurityEncryptionControl.builder()
                        .encriptionKey(cryptoPropertiesConf.getKey())
                        .encriptionIv(cryptoPropertiesConf.getIv())
                        .encriptionCipher(cryptoPropertiesConf.getCipherAlgorithm())
                        .encriptionInstance(cryptoPropertiesConf.getCipherInstance())
                        .build()
        );
        return encryption.encrypt(srtToEncrypt);
    }

    public String dencodeAESString(String srtToEncrypt) {
        ExternalAesEncryption encryption = new ExternalAesEncryption(
                CommonSecurityEncryptionControl.builder()
                        .encriptionKey(cryptoPropertiesConf.getKey())
                        .encriptionIv(cryptoPropertiesConf.getIv())
                        .encriptionCipher(cryptoPropertiesConf.getCipherAlgorithm())
                        .encriptionInstance(cryptoPropertiesConf.getCipherInstance())
                        .build()
        );
        return encryption.decrypt(srtToEncrypt);
    }

}
