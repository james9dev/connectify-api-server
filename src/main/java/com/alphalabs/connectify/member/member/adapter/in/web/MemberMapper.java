package com.alphalabs.connectify.member.member.adapter.in.web;

import com.alphalabs.connectify.member.member.domain.AuthDomain;
import com.alphalabs.connectify.member.member.domain.MemberDomain;
import org.springframework.stereotype.Component;


@Component
class MemberMapper {

    static MemberAuthResponseDto mapToMemberAuthResponse(AuthDomain authDomain) {

		MemberAuthResponseDto memberAuthResponseDto = new MemberAuthResponseDto(authDomain.getAuthTokenDto());

        return memberAuthResponseDto;
    }

	static MemberResponseDto mapToMemberResponse(MemberDomain domain) {

		MemberResponseDto memberResponseDto = new MemberResponseDto(domain.getNo(), domain.getEmail(), domain.getName());

		return memberResponseDto;
	}


}
