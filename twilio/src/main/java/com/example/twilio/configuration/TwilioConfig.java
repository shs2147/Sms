package com.example.twilio.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;



@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data


public class TwilioConfig {
	
	private String accountSid;
	private String authToken;
	private String trialNumber;
//	public String getAccountsid() {
//		return accountsid;
//	}
//	public void setAccountsid(String accountsid) {
//		this.accountsid = accountsid;
//	}
//	public String getAuthToken() {
//		return authToken;
//	}
//	public void setAuthToken(String authToken) {
//		this.authToken = authToken;
//	}
//	public String getTrialNumber() {
//		return trialNumber;
//	}
//	public void setTrialNumber(String trialNumber) {
//		this.trialNumber = trialNumber;
//	}

	
	
}
