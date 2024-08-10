package com.alphalabs.connectify.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthTokenDto {
	private AuthType authType;
	private String accessToken;
	private String refreshToken;

	public enum AuthType {
		SignIn,
		SignUp,
		Refresh
	}
}

