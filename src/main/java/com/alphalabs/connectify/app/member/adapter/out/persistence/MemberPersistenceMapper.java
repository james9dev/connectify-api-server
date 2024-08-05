package com.alphalabs.connectify.app.member.adapter.out.persistence;


import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
class MemberPersistenceMapper {

	MemberDomain mapToMemberDomain(MemberJpaEntity memberJpaEntity) {

		ProfileJpaEntity profileJpaEntity = memberJpaEntity.getProfile();

		ProfileDomain profileDomain = this.mapToProfileDomain(profileJpaEntity);

		return MemberDomain.withId(
				memberJpaEntity.getId(),
				memberJpaEntity.getEmail(),
				memberJpaEntity.getName(),
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
				profileJpaEntity.getLatitude(),
				profileJpaEntity.getLongitude(),
				profileJpaEntity.getPictures().stream().map(MemberPersistenceMapper::mapToProfilePicture).toList()
		);
	}

	static ProfileDomain.ProfilePicture mapToProfilePicture(PictureJpaEntity pictureJpaEntity) {
		return new ProfileDomain.ProfilePicture(
				pictureJpaEntity.getId(),
				pictureJpaEntity.getImageUrl(),
				pictureJpaEntity.getPictureOrder(),
				pictureJpaEntity.getCreatedAt());
	}

}
