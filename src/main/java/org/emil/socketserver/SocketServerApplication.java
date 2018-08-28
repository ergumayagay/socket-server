package org.emil.socketserver;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class SocketServerApplication {
	
	
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SocketServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SocketServerApplication.class, args);
	}
	

	@Bean
	public CommandLineRunner run(SocketServerProperties socketServerProperties, 
			SocketServerService socketServerService) {
		
		return args ->{
			
			socketServerService.start(socketServerProperties.getPort());		
		};
		
	}
	
	
}
