package com.alphalabs.connectify.app.member.application.service;

import com.alphalabs.connectify.app.member.application.port.in.GetProfileUseCase;
import com.alphalabs.connectify.app.member.application.port.in.UpdateProfileUseCase;
import com.alphalabs.connectify.app.member.application.port.in.command.UpdateProfileCommand;
import com.alphalabs.connectify.app.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.app.member.application.port.out.UpdateProfilePort;
import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import com.alphalabs.connectify.common.architecture.UseCase;
import com.alphalabs.connectify.common.security.JwtUtil;
import com.alphalabs.connectify.exception.NoSuchElementFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class ProfileService implements GetProfileUseCase, UpdateProfileUseCase {

	private final GetMemberPort getMemberPort;
	private final UpdateProfilePort updateProfilePort;

	@Override
	public MemberDomain getProfile(Long no) throws NoSuchElementFoundException {
		return getMemberPort.getMember(no).orElseThrow(() ->
				new NoSuchElementFoundException("Member not found")
		);
	}

	@Override
	public MemberDomain getProfileMe(String accessToken) throws NoSuchElementFoundException {
		Long no = JwtUtil.getId(accessToken);

		return this.getProfile(no);
	}

	@Override
	public ProfileDomain updateProfile(UpdateProfileCommand command) {

		Long no = JwtUtil.getId(command.getAccessToken());

		ProfileDomain profileDomain = new ProfileDomain(
				null,
				command.getNickName(),
				command.getGender(),
				command.getHeight(),
				command.getBirthyear(),
				command.getBirthday(),
				command.getLatitude(),
				command.getLongitude()
		);

		return updateProfilePort.updateProfile(no, profileDomain);
	}
}
