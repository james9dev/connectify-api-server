package com.alphalabs.connectify.app.member.application.port.out;

import com.alphalabs.connectify.app.member.domain.MemberDomain;

import java.util.Optional;

public interface GetMemberPort {

	Optional<MemberDomain> getMember(Long id);

	Optional<MemberDomain> getMember(String providerToken);
}
