package com.alphalabs.connectify.app.member.application.port.out;

import com.alphalabs.connectify.app.member.domain.MemberDistanceDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;

import java.util.List;
import java.util.Optional;

public interface GetMemberPort {
	Optional<MemberDomain> getMember(Long memberId);
	Optional<MemberDomain> getMemberByProvider(Long providerId);

	List<MemberDistanceDomain> findNearbyMembers(Long memberId, Long radius);
	List<MemberDomain> getIntroMembers(Long memberId);
}
