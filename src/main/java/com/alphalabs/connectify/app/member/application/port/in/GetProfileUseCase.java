package com.alphalabs.connectify.app.member.application.port.in;

import com.alphalabs.connectify.app.member.domain.MemberDistanceDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;

import java.util.List;

public interface GetProfileUseCase {

	MemberDomain getProfile(Long memberId);
	MemberDomain getProfileMe(String accessToken);

	List<MemberDistanceDomain> getNearbyMembers(String accessToken, Long radius);
	List<MemberDomain> getIntroMembers(String accessToken);
}
