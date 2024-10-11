package dev.study.multitransaction.config.db;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import dev.study.multitransaction.config.DatabaseProperties;
import dev.study.multitransaction.config.XaDataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
@EnableJpaRepositories(
        basePackages = {"dev.study.multitransaction.db2.board"},
        entityManagerFactoryRef = SecondDbConfiguration.ENTITY_MANAGER_BEAN_NAME,
        transactionManagerRef = XaDataSourceConfig.TRANSACTION_MANAGER_BEAN_NAME
)
public class SecondDbConfiguration {
    public static final String ENTITY_MANAGER_BEAN_NAME = "twoDBEntityManager";
    private static final String DATASOURCE_BEAN_NAME = "twoDataSource";
    private static final String DATASOURCE_PROPERTIES_PREFIX = "spring.datasource.two";
    private static final String HIBERNATE_PROPERTIES = "twoHibernateProperties";

    @Bean(name = ENTITY_MANAGER_BEAN_NAME)
    public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder builder, @Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource,
                                                                @Qualifier(HIBERNATE_PROPERTIES) DatabaseProperties.Hibernate hibernateProperties) {

        return builder.dataSource(dataSource).packages("dev.study.multitransaction.db2.board").persistenceUnit(ENTITY_MANAGER_BEAN_NAME)
                .properties(DatabaseProperties.Hibernate.propertiesToMap(hibernateProperties)).build();
    }

    @Bean(name = HIBERNATE_PROPERTIES)
    @ConfigurationProperties(DATASOURCE_PROPERTIES_PREFIX + ".hibernate")
    public DatabaseProperties.Hibernate hibernateProperties() {
        return new DatabaseProperties.Hibernate();
    }

    @Bean(name = DATASOURCE_BEAN_NAME)
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
    public DataSource dataSource() {
        return new AtomikosDataSourceBean();
    }
}
