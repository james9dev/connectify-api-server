package com.alphalabs.connectify.ref.archsample.member.adapter.out.persistence;


import com.alphalabs.connectify.ref.archsample.member.application.port.out.input.ArchSampleInsertDto;
import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
class ArchSamplePersistenceMapper {

	Optional<ArchSampleDomain> mapToDomainEntity(
			Optional<ArchSampleJpaEntity> sampleJpaEntity) {

		return Optional.of(ArchSampleDomain.withId(
				sampleJpaEntity.orElseThrow().getId(),
				sampleJpaEntity.orElseThrow().getEmail(),
				sampleJpaEntity.orElseThrow().getPassword(),
				sampleJpaEntity.orElseThrow().getName()));

	}

	ArchSampleJpaEntity mapToJpaEntity(ArchSampleInsertDto insertDto) {

		return new ArchSampleJpaEntity(
				null,
				insertDto.getEmail(),
				insertDto.getPassword(),
				insertDto.getName());
	}

}
