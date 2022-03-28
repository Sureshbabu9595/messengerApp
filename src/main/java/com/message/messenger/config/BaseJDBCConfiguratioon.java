package com.message.messenger.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BaseJDBCConfiguratioon {

	public BaseJDBCConfiguratioon() {
		super();
	}

	@Bean("dataSource")
	@ConfigurationProperties("spring.datasource.messenger")
	public DataSource getDataSource() {
		//return DataSourceBuilder.create().build();
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.h2.Driver");
	        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");

	        return dataSource;
	}

	@Autowired
	@Bean("namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedParameterJDBCTemplate(DataSource dataSource) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	return namedParameterJdbcTemplate;	
	}

}