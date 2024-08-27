package com.alphalabs.connectify.app.member.application.service;

import com.alphalabs.connectify.app.member.application.port.in.GetProfileUseCase;
import com.alphalabs.connectify.app.member.application.port.in.UpdateProfileUseCase;
import com.alphalabs.connectify.app.member.application.port.in.UploadPictureUseCase;
import com.alphalabs.connectify.app.member.application.port.in.command.UpdateProfileCommand;
import com.alphalabs.connectify.app.member.application.port.in.command.UploadPictureCommand;
import com.alphalabs.connectify.app.member.application.port.out.*;
import com.alphalabs.connectify.app.member.domain.MemberDistanceDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import com.alphalabs.connectify.common.architecture.UseCase;
import com.alphalabs.connectify.common.security.JwtUtil;
import com.alphalabs.connectify.exception.ForbiddenContent;
import com.alphalabs.connectify.exception.NoSuchElementFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@UseCase
@Transactional
public class ProfileService implements GetProfileUseCase, UpdateProfileUseCase, UploadPictureUseCase {

	private final GetMemberPort getMemberPort;
	private final UpdateProfilePort updateProfilePort;
	private final InsertPicturePort insertPicturePort;

	private final FileStoragePort fileStoragePort;
	private final DetectModerationPort detectModerationPort;

	@Override
	public MemberDomain getProfile(Long memberId) throws NoSuchElementFoundException {
		return getMemberPort.getMember(memberId).orElseThrow(() ->
				new NoSuchElementFoundException("Member not found")
		);
	}

	@Override
	public MemberDomain getProfileMe(String accessToken) throws NoSuchElementFoundException {
		Long memberId = JwtUtil.getId(accessToken);

		return this.getProfile(memberId);
	}

	@Override
	public ProfileDomain updateProfile(UpdateProfileCommand command) {

		Long no = JwtUtil.getId(command.getAccessToken());

		ProfileDomain profileDomain = new ProfileDomain(
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

	@Override
	public List<MemberDistanceDomain> getNearbyMembers(String accessToken, Long radius) {
		Long memberId = JwtUtil.getId(accessToken);

		return getMemberPort.findNearbyMembers(memberId, radius);
	}

	@Override
	public List<MemberDomain> getIntroMembers(String accessToken) {
		Long memberId = JwtUtil.getId(accessToken);

		return getMemberPort.getIntroMembers(memberId);
	}


	@Override
	public ProfileDomain.ProfilePicture uploadPicture(UploadPictureCommand uploadPictureCommand) throws IOException {

		Long memberId = JwtUtil.getId(uploadPictureCommand.getAccessToken());

		String pictureUrl = fileStoragePort.savePicture(uploadPictureCommand.getMultipartFile());

		List<String> moderationLabels = detectModerationPort.detectModerationLabelsByFileName(pictureUrl);

		if (moderationLabels.isEmpty()) {

			Integer order = uploadPictureCommand.getOrder();

			ProfileDomain.ProfilePicture profilePicture = insertPicturePort.insertPicture(memberId, pictureUrl, order);

			return profilePicture;
		} else {
			fileStoragePort.deletePicture(pictureUrl);

			throw new ForbiddenContent("You are not allowed to upload a picture");
		}
	}

	@Override
	public ProfileDomain.ProfilePicture uploadPictureNoncheck(UploadPictureCommand uploadPictureCommand) throws IOException {

		Long memberId = JwtUtil.getId(uploadPictureCommand.getAccessToken());

		String pictureUrl = fileStoragePort.savePicture(uploadPictureCommand.getMultipartFile());

		Integer order = uploadPictureCommand.getOrder();

		ProfileDomain.ProfilePicture profilePicture = insertPicturePort.insertPicture(memberId, pictureUrl, order);

		return profilePicture;
	}
}
