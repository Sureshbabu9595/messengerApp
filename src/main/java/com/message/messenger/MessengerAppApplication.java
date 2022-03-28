package com.message.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Generated from Spring Initilizer
 * 
 * @author sureshbabu
 *
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class MessengerAppApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/messenger");
		SpringApplication.run(MessengerAppApplication.class, args);
	}

}
