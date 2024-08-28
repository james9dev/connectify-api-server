package com.alphalabs.connectify.app.member.adapter.out.persistence;


import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
class MemberPersistenceMapper {

	@Value("${file.server.domain}")
	private String fileServerDomain;

	MemberDomain mapToMemberDomain(MemberJpaEntity memberJpaEntity) {

		ProfileJpaEntity profileJpaEntity = memberJpaEntity.getProfile();

		ProfileDomain profileDomain = this.mapToProfileDomain(profileJpaEntity);

		return MemberDomain.withId(
				memberJpaEntity.getId(),
				memberJpaEntity.getEmail(),
				memberJpaEntity.getName(),
				memberJpaEntity.getCreatedAt(),
				profileDomain);
	}

	ProfileDomain mapToProfileDomain(ProfileJpaEntity profileJpaEntity) {

		return new ProfileDomain(
				profileJpaEntity.getId(),
				profileJpaEntity.getNickName(),
				profileJpaEntity.getGender(),
				profileJpaEntity.getHeight(),
				profileJpaEntity.getBirthyear(),
				profileJpaEntity.getBirthday(),
				profileJpaEntity.getJob(),
				profileJpaEntity.getCompany(),
				profileJpaEntity.getEducationInstitution(),
				profileJpaEntity.getEducationInstitution(),
				profileJpaEntity.getLatitude(),
				profileJpaEntity.getLongitude(),
				profileJpaEntity.getLocation(),
				profileJpaEntity.getBio(),
				profileJpaEntity.getPictures().stream().map(this::mapToProfilePicture).toList()
		);
	}

	ProfileDomain.ProfilePicture mapToProfilePicture(PictureJpaEntity pictureJpaEntity) {

		String encodedUrl = URLEncoder.encode((fileServerDomain + pictureJpaEntity.getImageUrl()), StandardCharsets.UTF_8);

		return new ProfileDomain.ProfilePicture(
				pictureJpaEntity.getId(),
				encodedUrl,
				pictureJpaEntity.getPictureOrder(),
				pictureJpaEntity.getCreatedAt());
	}
}
