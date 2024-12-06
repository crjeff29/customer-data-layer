package com.bda.customer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "base.encryption")
@Data
public class CryptoPropertiesConf {
    private String key;
    private String iv;
    private int length;
    private String cipherAlgorithm;
    private String secretKeyFactory;
    private String salt;
    private String cipherInstance;
    private int interactions;
}
