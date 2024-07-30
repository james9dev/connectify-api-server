package com.alphalabs.connectify.app.member.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class MemberSignUpRequestDto {
	private final String email;
	private final String password;
	private final String name;
}
