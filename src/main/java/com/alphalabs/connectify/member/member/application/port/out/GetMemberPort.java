package com.alphalabs.connectify.member.member.application.port.out;

import com.alphalabs.connectify.member.member.domain.MemberDomain;

import java.util.Optional;

public interface GetMemberPort {

	Optional<MemberDomain> getMember(Long id);

	Optional<MemberDomain> getMember(String providerToken);
}
