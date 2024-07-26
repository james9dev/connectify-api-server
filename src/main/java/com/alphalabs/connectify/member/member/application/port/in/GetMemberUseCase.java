package com.alphalabs.connectify.member.member.application.port.in;

import com.alphalabs.connectify.member.member.domain.MemberDomain;

public interface GetMemberUseCase {

	MemberDomain getMember(Long id);
}
