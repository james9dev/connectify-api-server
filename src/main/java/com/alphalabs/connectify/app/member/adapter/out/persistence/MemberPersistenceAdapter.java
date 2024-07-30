package com.alphalabs.connectify.app.member.adapter.out.persistence;

import com.alphalabs.connectify.app.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.app.member.application.port.out.InsertMemberPort;
import com.alphalabs.connectify.app.member.domain.KakaoDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.common.architecture.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class MemberPersistenceAdapter implements InsertMemberPort, GetMemberPort {

	private final MemberRepository repository;
	private final MemberPersistenceMapper mapper;

	@Override
	public Long insertMember(MemberDomain domain) {

		MemberJpaEntity jpaEntity = new MemberJpaEntity(
				domain.getEmail(),
				domain.getName());

		return repository.save(jpaEntity).getNo();
	}

	@Override
	public Long insertKakaoUser(KakaoDomain domain) {
		MemberJpaEntity jpaEntity = new MemberJpaEntity(
				domain.getKakao_account().getEmail(),
				domain.getAccess_token(),
				ProviderType.KAKAO);

		return repository.save(jpaEntity).getNo();
	}

	@Override
	public Optional<MemberDomain> getMember(Long id) {

		Optional<MemberJpaEntity> jpaEntity = repository.findById(id);

		if (jpaEntity.isPresent()) {
			return mapper.mapToDomainEntity(jpaEntity.get());
		}

		return Optional.empty();
	}

	@Override
	public Optional<MemberDomain> getMember(String providerToken) {

		Optional<MemberJpaEntity> jpaEntity = repository.findByProviderToken(providerToken);

		if (jpaEntity.isPresent()) {
			return mapper.mapToDomainEntity(jpaEntity.get());
		}

		return Optional.empty();
	}
}
