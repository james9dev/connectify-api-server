package com.alphalabs.connectify.app.member.adapter.out.persistence;

import com.alphalabs.connectify.app.member.application.port.out.UpdateProfilePort;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import com.alphalabs.connectify.app.member.domain.enums.GenderType;
import com.alphalabs.connectify.app.member.domain.enums.ProviderType;
import com.alphalabs.connectify.app.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.app.member.application.port.out.InsertMemberPort;
import com.alphalabs.connectify.app.member.domain.KakaoDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.common.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class MemberPersistenceAdapter implements InsertMemberPort, GetMemberPort, UpdateProfilePort {

	private final MemberPersistenceMapper mapper;

	private final MemberRepository memberRepository;
	private final ProfileRepository profileRepository;


	@Override
	public Long insertKakaoUser(KakaoDomain kakaoDomain) {

		KakaoDomain.KakaoAccount kakaoAccount = kakaoDomain.getKakao_account();

		MemberJpaEntity jpaEntity = new MemberJpaEntity(
				kakaoAccount.getEmail(),
				kakaoAccount.getIs_email_verified(),
				kakaoDomain.getAccess_token(),
				ProviderType.KAKAO);


		ProfileJpaEntity profileEntity = new ProfileJpaEntity();

		String gender = kakaoAccount.getGender();

		if (gender != null) {
			GenderType genderType = GenderType.MALE.toString().equals(gender) ? GenderType.MALE : GenderType.FEMALE;
			profileEntity.setGender(genderType);
		}

		profileRepository.save(profileEntity);

		jpaEntity.setProfile(profileEntity);

		return memberRepository.save(jpaEntity).getNo();
	}

	@Override
	public Optional<MemberDomain> getMember(Long no) {

		Optional<MemberJpaEntity> jpaEntity = memberRepository.findById(no);

		return jpaEntity.map(mapper::mapToMemberDomain);

	}

	@Override
	public Optional<MemberDomain> getMember(String providerToken) {

		Optional<MemberJpaEntity> jpaEntity = memberRepository.findByProviderToken(providerToken);

		return jpaEntity.map(mapper::mapToMemberDomain);
	}


	@Override
	public ProfileDomain updateProfile(Long memberNo, ProfileDomain domain) {

		Optional<MemberJpaEntity> jpaEntity = memberRepository.findById(memberNo);

		if (jpaEntity.isPresent()) {
			MemberJpaEntity memberJpaEntity = jpaEntity.get();

			ProfileJpaEntity profileEntity = memberJpaEntity.getProfile();

			String nickName = domain.getNickName();
			GenderType gender = domain.getGender();
			Integer height = domain.getHeight();
			String birthyear = domain.getBirthyear();
			String birthday = domain.getBirthday();
			Double latitude = domain.getLatitude();
			Double longitude = domain.getLongitude();


			if (!nickName.isEmpty() && !nickName.equals(profileEntity.getNickName())) {
				profileEntity.setNickName(nickName);
			}

			if (gender != null && !gender.equals(profileEntity.getGender())) {
				profileEntity.setGender(gender);
			}

			if (height != null && !height.equals(profileEntity.getHeight())) {
				profileEntity.setHeight(height);
			}

			if (birthyear != null && !birthyear.equals(profileEntity.getBirthyear())) {
				profileEntity.setBirthyear(birthyear);
			}

			if (birthday != null && !birthday.equals(profileEntity.getBirthday())) {
				profileEntity.setBirthday(birthday);
			}

			if (latitude != null && !latitude.equals(profileEntity.getLatitude())) {
				profileEntity.setLatitude(latitude);
			}

			if (longitude != null && !longitude.equals(profileEntity.getLongitude())) {
				profileEntity.setLongitude(longitude);
			}

			ProfileJpaEntity savedProfile = profileRepository.save(profileEntity);

			return mapper.mapToProfileDomain(savedProfile);
		}

		return null;
	}
}
