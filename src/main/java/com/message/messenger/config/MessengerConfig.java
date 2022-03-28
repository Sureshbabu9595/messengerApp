package com.message.messenger.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

@PropertySource("classpath:messenger-queries.xml")
@Configuration
public class MessengerConfig {

	private Logger LOGGER = LoggerFactory.getLogger(MessengerConfig.class);

	@Bean("handler")
	public DeserializationProblemHandler getDeserializationProblemHandler() {
		DeserializationProblemHandler handler = new DeserializationProblemHandler() {
			@Override
			public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p,
					JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
				LOGGER.info(String.format("The property %s is Unknown for %s", propertyName, beanOrClass.getClass()));
				return true;
			}

		};

		return handler;
	}
	
	@Bean("requestSchemaMap")
	public Map<String,String> getRequestSchemaMap(){
		Map<String,String> returnValue = new HashMap<>();
		returnValue.put("/messenger/sendEmail", "classpath:/schema/request/sendEmailRequest.json");
		return returnValue;
	}
	
	@Bean("responseSchemaMap")
	public Map<String,String> getResponseSchemaMap(){
		Map<String,String> returnValue = new HashMap<>();
		returnValue.put("/messenger/getMessages", "classpath:/schema/response/getMessages.json");
		return returnValue;
	}
	
	

}
