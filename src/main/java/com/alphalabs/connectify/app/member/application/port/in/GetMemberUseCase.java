package com.alphalabs.connectify.app.member.application.port.in;

import com.alphalabs.connectify.app.member.domain.MemberDomain;

public interface GetMemberUseCase {

	MemberDomain getMember(Long id);
}
