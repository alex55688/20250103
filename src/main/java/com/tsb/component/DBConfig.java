package com.tsb.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

@Configuration
public class DBConfig {

	@Autowired
	private Environment env;
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public SQLServerDataSource datasource() {
		SQLServerDataSource datasource = new SQLServerDataSource();
		datasource.setURL(env.getProperty("spring.datasource.url"));
		datasource.setUser(env.getProperty("spring.datasource.username"));
		datasource.setPassword(env.getProperty("spring.datasource.password"));
		return datasource;
	}
	
}
