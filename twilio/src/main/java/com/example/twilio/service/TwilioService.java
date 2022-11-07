package com.example.twilio.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.twilio.configuration.TwilioConfig;
import com.example.twilio.dto.PasswordResetRequestDto;
import com.example.twilio.dto.PasswordResetResponseDto;
import com.example.twilio.dto.otpStatus;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import reactor.core.publisher.Mono;

@Service

public class TwilioService {
	@Autowired
	TwilioConfig twilioConfig;

	Map<String, String> otpMap=new HashMap<>();

	public Mono<PasswordResetResponseDto> passwordReset(PasswordResetRequestDto passwordResetRequestDto) {
		
		PasswordResetResponseDto passwordResetResponseDto=null;
		
		try {
		PhoneNumber to=new PhoneNumber(passwordResetRequestDto.getPhoneNumber());
		PhoneNumber from=new PhoneNumber(twilioConfig.getTrialNumber());
		String otp=generateOtp();
		String otpMessage="here is otp for reset" +otp+" use to rest it" ;
		
		Message message = Message
				.creator(to,from,
//                new PhoneNumber("+15558675310"),
//                new PhoneNumber("+15017122661"),
                otpMessage)
            .create();
		otpMap.put(passwordResetRequestDto.getUserName(), otp);
		passwordResetResponseDto=new PasswordResetResponseDto(otpStatus.DELEVERED,otpMessage);
		
	}catch (Exception ex){
		passwordResetResponseDto=new PasswordResetResponseDto(otpStatus.FAILED,ex.getMessage());
	}
		
		return  Mono.just(passwordResetResponseDto);
		
	}
	private String generateOtp() {
		return new DecimalFormat("0000")
				.format(new Random().nextInt(9999));
		
	}
	public Mono<String> validateOtp(String inputOtp,String userName){
		if(inputOtp.equals(otpMap.get(userName))) {
			return Mono.just("success");
		}else{
			return Mono.error(new IllegalArgumentException("invalid otp"));
		}
		
		
	}
	

	
}
