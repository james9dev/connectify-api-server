package com.alphalabs.connectify.member.member.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class MemberSignUpKakaoRequestDto {
	private final String idToken;
	private final String accessToken;
	private final String tokenType;

}
