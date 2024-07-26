package com.alphalabs.connectify.member.member.adapter.in.web;

import com.alphalabs.connectify.member.member.domain.MemberDomain;
import org.springframework.stereotype.Component;


@Component
class MemberMapper {

    static MemberSignUpResponseDto mapToRegisterResponse(Long id) {

		MemberSignUpResponseDto registerResponseDto = new MemberSignUpResponseDto(id);

        return registerResponseDto;
    }

	static MemberResponseDto mapToMemberResponse(MemberDomain domain) {

		MemberResponseDto memberResponseDto = new MemberResponseDto(domain.getId(), domain.getEmail(), domain.getName());

		return memberResponseDto;
	}


}
