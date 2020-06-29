package com.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@PropertySource(value = { "classpath:jdbc.properties", "classpath:messages.properties" })
@ComponentScan(basePackages = { "com.app" })
public class SpringXmlConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	Environment env;

	/*
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver
	 * viewResolver = new InternalResourceViewResolver();
	 * viewResolver.setPrefix("/WEB-INF/views/"); viewResolver.setSuffix(".jsp");
	 * return viewResolver; }  
	 */

	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesconfigure() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setCheckRefresh(true);
		tilesConfigurer.setDefinitionsFactoryClass(DefinitionsConfiguration.class);
		DefinitionsConfiguration.addDefinitions();
		return tilesConfigurer;
	}

	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dailect", env.getRequiredProperty("hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.app.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transctionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
