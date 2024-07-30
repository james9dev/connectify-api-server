package com.alphalabs.connectify.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthTokenDto {
	//private String grantType;
	private String accessToken;
	private String refreshToken;
}