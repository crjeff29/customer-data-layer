package com.bda.customer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "db.data")
@Data
public class DataBasePropertiesConf {
    private String url;
    private String user;
    private String pass;
    private String driver;
}
