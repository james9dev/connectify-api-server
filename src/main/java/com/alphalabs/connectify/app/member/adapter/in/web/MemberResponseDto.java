package com.alphalabs.connectify.app.member.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
class MemberResponseDto {

	@Getter
	private Long id;

	@Getter
	private final String email;

	@Getter
	private final String name;
}
