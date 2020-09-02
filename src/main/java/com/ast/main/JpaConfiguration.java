package com.ast.main;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ast.HealthCare.DataBase.DatabaseConfigurator;

@Configuration
@EnableTransactionManagement
public class JpaConfiguration {

	private static final Logger LOG = Logger.getLogger(JpaConfiguration.class.getName());

	@Bean
	public EnvironmentStringPBEConfig environmentVariablesConfiguration() {
		System.out.println("environment variables configuration method");
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		config.setPassword("AST");
		return config;
	}

	@Bean
	public PooledPBEStringEncryptor stringEncryptor() {
		System.out.println("string encryptor method");
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setConfig(environmentVariablesConfiguration());
		return encryptor;
	}

	@Bean
	public PropertyPlaceholderConfigurer propertyConfigurer() {
		System.out.println("property configurer method12");
		EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(
				stringEncryptor());
		propertyConfigurer.setLocation(new ClassPathResource("app.properties"));
		return propertyConfigurer;
	}

	@Bean
	public Config getProperties() {
		System.out.println("get properties method1");
		// propertyConfigurer();
		return new Config();
	}

	@Bean
	public DataSource dataSource() {
		System.out.println("datasource method0");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(getProperties().getDriverName());
		System.out.println("main 2" + getProperties().getDriverName());
		dataSource.setUrl(getProperties().getUrl());
		dataSource.setUsername(getProperties().getUserName());
		dataSource.setPassword(getProperties().getPassword());
		DatabaseConfigurator databaseConfigurator = new DatabaseConfigurator();
		databaseConfigurator.setDataSource(dataSource, getProperties().getDataBaseName());
		try {
			databaseConfigurator.checkDatabase();
		} catch (SQLException ex) {
			LOG.error("Error in Auto Config Database", ex);
		}
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		System.out.println("haritam" + dataSource);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Bean
	public SimpleJdbcCall simpleJdbcCall(DataSource dataSource) {
		System.out.println("hari" + dataSource);

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource);
		return simpleJdbcCall;
	}
	/*
	 * @Bean public AuthenticationEntryPoint securityEntry() { return new
	 * AuthenticationEntryPoint(); }
	 */

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getTransactionManager() {
		System.out.println("datasource gettransactionmanager");
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		DataSource dataSource = this.dataSource();
		txManager.setDataSource(dataSource);

		return txManager;
	}

	@Bean
	public JavaMailSenderImpl mailSender() {
		System.out.println("javamail");
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(getProperties().getHost());
		System.out.println(getProperties().getHost());
		sender.setPort(getProperties().getPort());
		System.out.println(getProperties().getPort());
		sender.setUsername(getProperties().getMailUserName());
		System.out.println(getProperties().getMailUserName());
		sender.setPassword(getProperties().getMailPassword());
		System.out.println(getProperties().getMailPassword());
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "false");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.ssl.enable", "false");
		/* properties.put("mail.smtp.starttls.enable","true"); */
		sender.setJavaMailProperties(properties);
		return sender;
	}

	/*
	 * <bean id="mailSender"
	 * class="org.springframework.mail.javamail.JavaMailSenderImpl"> <property
	 * name="host" value="smtp.gmail.com" /> <property name="port" value="587" />
	 * <property name="username" value="ast.baranikumar@gmail.com" /> <property
	 * name="password" value="Diva8028" /> <property name="javaMailProperties">
	 * <props> <prop key="mail.transport.protocol">smtp</prop> <prop
	 * key="mail.smtp.auth">true</prop> <prop
	 * key="mail.smtp.starttls.enable">true</prop> </props> </property> </bean>
	 */
	@Bean
	/*
	 * public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws
	 * NamingException { System.out.println("entity manager factory method");
	 * LocalContainerEntityManagerFactoryBean factoryBean = new
	 * LocalContainerEntityManagerFactoryBean();
	 * factoryBean.setDataSource(dataSource()); factoryBean.setPackagesToScan(new
	 * String[]{"com.ast.TempleTicket.Pojo"});
	 * factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	 * factoryBean.setJpaProperties(jpaProperties()); return factoryBean; }
	 */

	/*
	 * Provider specific adapter.
	 */
	/*
	 * @Bean public JpaVendorAdapter jpaVendorAdapter() { HibernateJpaVendorAdapter
	 * hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter(); return
	 * hibernateJpaVendorAdapter; }
	 */

	/*
	 * Here you can specify any provider specific properties.
	 */
	public Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", getProperties().getDialect());
		properties.put("hibernate.show_sql", getProperties().getShowSQL());
		properties.put("hibernate.format_sql", getProperties().getFormatSQL());
		return properties;
	}

	/*
	 * @Bean
	 * 
	 * @Autowired public PlatformTransactionManager
	 * transactionManager(EntityManagerFactory emf) { JpaTransactionManager
	 * txManager = new JpaTransactionManager();
	 * txManager.setEntityManagerFactory(emf); return txManager; }
	 */
	/*
	 * @Bean public MappingJackson2HttpMessageConverter jacksonMapper() {
	 * MappingJackson2HttpMessageConverter jm = new
	 * MappingJackson2HttpMessageConverter(); jm.setObjectMapper(new
	 * HibernateAwareObjectMapper()); return jm; }
	 */
}
