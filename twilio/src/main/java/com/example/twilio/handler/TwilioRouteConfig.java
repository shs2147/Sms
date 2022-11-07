package com.example.twilio.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class TwilioRouteConfig {
	@Autowired
	TwilioOtpHandler otpHandler;
	

    @Bean
    public RouterFunction<ServerResponse> handleSMS() {
        return RouterFunctions
        		.route()
                .POST("/router/sendOTP", otpHandler::sendOTP)
                .POST("/router/validateOTP", otpHandler::validateOTP)
                .build();
    }

}
