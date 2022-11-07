package com.example.twilio;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.twilio.configuration.TwilioConfig;
import com.twilio.Twilio;

@SpringBootApplication
public class TwilioApplication {
	
	@Autowired
	TwilioConfig twilioConfig;
	
	@PostConstruct
	public void initTwilio() {
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
		
	}

	public static void main(String[] args) {
		SpringApplication.run(TwilioApplication.class, args);
	}

}
