package com.example.twilio.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.BodyInserters;
import com.example.twilio.dto.PasswordResetRequestDto;
import com.example.twilio.service.TwilioService;

import reactor.core.publisher.Mono;

@Component
public class TwilioOtpHandler {
	 @Autowired
	    private TwilioService service;

	    public Mono<ServerResponse> sendOTP(ServerRequest serverRequest) {
	        return serverRequest.bodyToMono(PasswordResetRequestDto.class)
	        		.flatMap(dtoo -> service.passwordReset(dtoo))
	        		.flatMap(dtoo -> ServerResponse.status(HttpStatus.OK)
	        				.body(BodyInserters.fromValue(dtoo)));
	    }

	    public Mono<ServerResponse> validateOTP(ServerRequest serverRequest) {
	        return serverRequest.bodyToMono(PasswordResetRequestDto.class)
	                .flatMap(dto -> service.validateOtp(dto.getOtp(),dto.getUserName()))
	                .flatMap(dto -> ServerResponse.status(HttpStatus.OK)
	                .body(BodyInserters.fromValue(dto)));
	    }

}
