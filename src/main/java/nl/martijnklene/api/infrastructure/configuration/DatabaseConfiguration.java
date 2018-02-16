package nl.martijnklene.api.infrastructure.configuration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration extends HibernateJpaAutoConfiguration {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    public DatabaseConfiguration(DataSource dataSource, JpaProperties jpaProperties, ObjectProvider<JtaTransactionManager> jtaTransactionManager, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, jpaProperties, jtaTransactionManager, transactionManagerCustomizers);
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder factoryBuilder) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = super.entityManagerFactory(factoryBuilder);
        entityManagerFactoryBean.setMappingResources(
                "META-INF/application-orm.xml"
        );
        return entityManagerFactoryBean;
    }
}