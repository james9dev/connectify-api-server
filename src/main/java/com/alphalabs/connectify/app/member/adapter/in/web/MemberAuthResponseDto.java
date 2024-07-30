package com.alphalabs.connectify.app.member.adapter.in.web;

import com.alphalabs.connectify.common.AuthTokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
class MemberAuthResponseDto {

	@Getter
	private AuthTokenDto authToken;

}
