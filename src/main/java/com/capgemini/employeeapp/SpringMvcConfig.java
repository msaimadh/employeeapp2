package com.capgemini.employeeapp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.capgemini.employeeapp")
public class SpringMvcConfig {

	
	
@Bean	
 public DataSource getDataSource() {
	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	 dataSource.setUrl("jdbc:mysql://localhost/madhuri");
	 dataSource.setUsername("root");
	 dataSource.setPassword("root");
	 return dataSource;
 }

@Bean
public JdbcTemplate getJdbcTemplate() {
	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
	return jdbcTemplate;
	
}
	@Bean
	public ViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
	return viewResolver;
	}

}