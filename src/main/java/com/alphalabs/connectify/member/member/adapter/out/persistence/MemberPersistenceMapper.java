package com.alphalabs.connectify.member.member.adapter.out.persistence;


import com.alphalabs.connectify.member.member.domain.MemberDomain;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
class MemberPersistenceMapper {

	Optional<MemberDomain> mapToDomainEntity(Optional<MemberJpaEntity> sampleJpaEntity) {

		return Optional.of(MemberDomain.withNo(
				sampleJpaEntity.get().getNo(),
				sampleJpaEntity.get().getEmail(),
				sampleJpaEntity.get().getName()));

	}

}
