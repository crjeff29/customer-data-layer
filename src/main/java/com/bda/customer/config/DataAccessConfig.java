package com.bda.customer.config;

import com.bda.customer.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.bda.customer.repository"},
        entityManagerFactoryRef = "bdaEntityManager",
        transactionManagerRef = "oracleTransactionManager"
)
@RequiredArgsConstructor
public class DataAccessConfig {
    private final Utilities utilities;
    private final DataBasePropertiesConf dataBasePropertiesConf;

    @Bean
    public DataSource getOracleDS() throws SQLException {

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dataBasePropertiesConf.getDriver());
        dataSourceBuilder.url(utilities.dencodeAESString(dataBasePropertiesConf.getUrl()));
        dataSourceBuilder.username(utilities.dencodeAESString(dataBasePropertiesConf.getUser()));
        dataSourceBuilder.password(utilities.dencodeAESString(utilities.base64Decode(dataBasePropertiesConf.getPass())));
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean bdaEntityManager() throws SQLException {
        LocalContainerEntityManagerFactoryBean entityManager
                = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(getOracleDS());
        entityManager.setPackagesToScan("com.bda.customer.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl-auto", "none");
        //properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        properties.put("hibernate.globally_quoted_identifiers", true);
        properties.put("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put("hibernate.naming.implicit-strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl");
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Bean
    @Primary
    public PlatformTransactionManager oracleTransactionManager(
            final @Qualifier("bdaEntityManager") LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(oracleEntityManagerFactory.getObject()));
    }

}
