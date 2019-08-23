package com.everis.d4i.tutorial.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.everis.d4i.tutorial.repositories")
@PropertySource("application.properties")
@EnableTransactionManagement
//@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class H2JpaConfig {

	@Autowired
	private Environment env;

	// private String filePath = new File("").getAbsolutePath();

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.user"));
		dataSource.setPassword(env.getProperty("spring.datasource.pass"));

		final Resource initSchema = new ClassPathResource("schema.sql");

		final DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);

		return dataSource;
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();

		hibernateProperties.put("spring.jpa.hibernate.ddl-auto",
				env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));

		hibernateProperties.setProperty("spring.jpa.hibernate.use-new-id-generator-mappings",
				env.getProperty("spring.jpa.hibernate.use-new-id-generator-mappings"));

		hibernateProperties.setProperty("spring.jpa.properties.hibernate.format_sql",
				env.getProperty("spring.jpa.properties.hibernate.format_sql"));

		hibernateProperties.setProperty("spring.jpa.properties.hibernate.show_sql",
				env.getProperty("spring.jpa.properties.hibernate.show_sql"));

		hibernateProperties.setProperty("spring.jpa.database", env.getProperty("spring.jpa.database"));

		// hibernateProperties.setProperty("hibernate.format_sql",
		// env.getProperty("hibernate.format_sql"));

//		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		return hibernateProperties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.everis.d4i.tutorial.entities" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

}
