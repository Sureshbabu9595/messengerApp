package com.message.messenger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.message.messenger.dao.EmailDao;
import com.message.messenger.dao.H2EmailDao;
import com.message.messenger.dao.IEmailDao;

@Configuration
public class MessengerJDBCConfiguration extends BaseJDBCConfiguratioon {
	
	@Profile({"test","local"})
	@Bean("emailDao")
	public IEmailDao getEmailDao() {
		return new EmailDao();
	}
	
	@Profile("testWithH2DB")
	@Autowired
	@Bean("emailDao")
	public IEmailDao getH2EmailDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		return new H2EmailDao(namedParameterJdbcTemplate);
	}
}
