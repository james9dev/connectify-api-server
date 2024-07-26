package com.alphalabs.connectify.member.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoAccount {
	private final String email;

	private final Boolean is_email_valid;
	private final Boolean is_email_verified;
}
