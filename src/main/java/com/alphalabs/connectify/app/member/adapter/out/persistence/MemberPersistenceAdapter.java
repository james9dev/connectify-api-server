package com.alphalabs.connectify.app.member.adapter.out.persistence;

import com.alphalabs.connectify.app.member.application.port.out.InsertPicturePort;
import com.alphalabs.connectify.app.member.application.port.out.UpdateProfilePort;
import com.alphalabs.connectify.app.member.domain.MemberDistanceDomain;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import com.alphalabs.connectify.app.member.domain.enums.GenderType;
import com.alphalabs.connectify.app.member.domain.enums.ProviderType;
import com.alphalabs.connectify.app.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.app.member.application.port.out.InsertMemberPort;
import com.alphalabs.connectify.app.member.domain.KakaoDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.common.architecture.PersistenceAdapter;
import com.alphalabs.connectify.exception.NoSuchElementFoundException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class MemberPersistenceAdapter implements InsertMemberPort, GetMemberPort, UpdateProfilePort, InsertPicturePort {

	private final MemberPersistenceMapper mapper;

	private final MemberRepository memberRepository;
	private final ProfileRepository profileRepository;
	private final PictureRepository pictureRepository;


	@Override
	public Long insertKakaoUser(KakaoDomain kakaoDomain) {

		KakaoDomain.KakaoAccount kakaoAccount = kakaoDomain.getKakao_account();

		MemberJpaEntity member = new MemberJpaEntity(
				kakaoAccount.getEmail(),
				kakaoAccount.getIs_email_verified(),
				kakaoDomain.getAccess_token(),
				ProviderType.KAKAO);

		memberRepository.save(member);

		ProfileJpaEntity profile = new ProfileJpaEntity();
		profile.setMember(member);

		String gender = kakaoAccount.getGender();

		if (gender != null) {
			GenderType genderType = GenderType.MALE.toString().equals(gender) ? GenderType.MALE : GenderType.FEMALE;
			profile.setGender(genderType);
		}

		member.setProfile(profile);

		profileRepository.save(profile);

		return member.getId();
	}

	@Override
	public Optional<MemberDomain> getMember(Long memberId) {

		Optional<MemberJpaEntity> jpaEntity = memberRepository.findById(memberId);

		return jpaEntity.map(mapper::mapToMemberDomain);

	}

	@Override
	public Optional<MemberDomain> getMember(String providerToken) {

		Optional<MemberJpaEntity> jpaEntity = memberRepository.findByProviderToken(providerToken);

		return jpaEntity.map(mapper::mapToMemberDomain);
	}

	@Override
	public ProfileDomain updateProfile(Long memberId, ProfileDomain domain) {

		Optional<MemberJpaEntity> jpaEntity = memberRepository.findById(memberId);

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

	@Override
	public List<MemberDistanceDomain> findNearbyUsers(Long memberId, Long radius) {

		Optional<MemberJpaEntity> targetMember = memberRepository.findById(memberId);
		if (targetMember.isPresent()) {
			Double latitude = targetMember.get().getProfile().getLatitude();
			Double longitude = targetMember.get().getProfile().getLongitude();

			 memberRepository.findNearbyMembers(latitude, longitude, radius);

			List<MemberDistance> results = memberRepository.findNearbyMembers(latitude, longitude, radius);
			List<MemberDistanceDomain> nearbyMembers = new ArrayList<>();

			for (MemberDistance memberDistance : results) {
				MemberJpaEntity memberJpaEntity = memberDistance.getMember();
				double distance = memberDistance.getDistance(); // 필요시 거리 정보도 사용할 수 있습니다.

				MemberDomain memberDomain = mapper.mapToMemberDomain(memberJpaEntity);

				MemberDistanceDomain memberDistanceDomain = new MemberDistanceDomain(memberDomain, distance);

				nearbyMembers.add(memberDistanceDomain);
			}

			return nearbyMembers;
		}

		return null;
	}

	@Override
	public ProfileDomain.ProfilePicture insertPicture(Long memberId, String filePath, Integer order) {

		ProfileJpaEntity profileEntity = profileRepository.findByMemberId(memberId);

		if (profileEntity == null) {
			throw new NoSuchElementFoundException("Not found profile");
		}

		PictureJpaEntity pictureEntity = new PictureJpaEntity();

		pictureEntity.setPictureOrder(order);
		pictureEntity.setImageUrl(filePath);

		pictureEntity.setProfile(profileEntity);

		pictureRepository.save(pictureEntity);


		return new ProfileDomain.ProfilePicture(pictureEntity.getId(), pictureEntity.getImageUrl(), pictureEntity.getPictureOrder(), pictureEntity.getCreatedAt());
	}
}
