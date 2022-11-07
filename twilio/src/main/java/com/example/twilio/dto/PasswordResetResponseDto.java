package com.example.twilio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PasswordResetResponseDto {
	private otpStatus status;
	private String message;

}
