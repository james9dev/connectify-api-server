package com.alphalabs.connectify.app.member.application.port.in;

import com.alphalabs.connectify.app.member.domain.MemberDomain;

public interface GetProfileUseCase {

	MemberDomain getProfile(Long no);
	MemberDomain getProfileMe(String accessToken);
}
