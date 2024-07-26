package com.alphalabs.connectify.member.member.adapter.in.web;

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
