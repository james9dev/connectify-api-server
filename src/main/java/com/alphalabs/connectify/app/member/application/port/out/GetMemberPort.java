package com.alphalabs.connectify.app.member.application.port.out;

import com.alphalabs.connectify.app.member.domain.MemberDistanceDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;

import java.util.List;
import java.util.Optional;

public interface GetMemberPort {
	Optional<MemberDomain> getMember(Long no);
	Optional<MemberDomain> getMember(String providerToken);

	List<MemberDistanceDomain> findNearbyUsers(Long memberNo, Long radius);
}
