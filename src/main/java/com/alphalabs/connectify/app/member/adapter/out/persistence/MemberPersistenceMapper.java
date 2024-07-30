package com.alphalabs.connectify.app.member.adapter.out.persistence;


import com.alphalabs.connectify.app.member.domain.MemberDomain;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
class MemberPersistenceMapper {

	Optional<MemberDomain> mapToDomainEntity(MemberJpaEntity sampleJpaEntity) {

		return Optional.of(MemberDomain.withNo(
				sampleJpaEntity.getNo(),
				sampleJpaEntity.getEmail(),
				sampleJpaEntity.getName()));

	}

}
