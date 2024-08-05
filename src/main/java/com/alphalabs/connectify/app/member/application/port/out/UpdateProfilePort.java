package com.alphalabs.connectify.app.member.application.port.out;

import com.alphalabs.connectify.app.member.domain.ProfileDomain;

public interface UpdateProfilePort {
	ProfileDomain updateProfile(Long memberId, ProfileDomain domain);
}
