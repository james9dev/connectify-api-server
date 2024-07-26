package com.alphalabs.connectify.member.member.adapter.out.persistence;

import com.alphalabs.connectify.member.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.member.member.application.port.out.InsertMemberPort;
import com.alphalabs.connectify.member.member.domain.KakaoDomain;
import com.alphalabs.connectify.member.member.domain.MemberDomain;
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
				null,
				domain.getEmail(),
				domain.getName(),
				null,
				null);

		return repository.save(jpaEntity).getId();
	}

	@Override
	public Long insertKakaoUser(KakaoDomain domain) {
		MemberJpaEntity jpaEntity = new MemberJpaEntity(
				null,
				domain.getKakao_account().getEmail(),
				null,
				domain.getAccess_token(),
				ProviderType.KAKAO);

		return repository.save(jpaEntity).getId();
	}

	@Override
	public Optional<MemberDomain> getMember(Long id) {

		Optional<MemberJpaEntity> jpaEntity = repository.findById(id);

		return mapper.mapToDomainEntity(jpaEntity);
	}

	@Override
	public Optional<MemberDomain> getMember(String providerToken) {

		Optional<MemberJpaEntity> jpaEntity = repository.findByProviderToken(providerToken);

		if (jpaEntity.isPresent()) {
			return mapper.mapToDomainEntity(jpaEntity);
		}

		return Optional.empty();
	}
}
