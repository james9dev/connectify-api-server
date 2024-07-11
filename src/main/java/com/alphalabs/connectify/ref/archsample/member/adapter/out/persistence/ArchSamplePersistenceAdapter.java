package com.alphalabs.connectify.ref.archsample.member.adapter.out.persistence;

import com.alphalabs.connectify.common.PersistenceAdapter;
import com.alphalabs.connectify.ref.archsample.member.application.port.out.ArchSampleInsertPort;
import com.alphalabs.connectify.ref.archsample.member.application.port.out.ArchSampleGetPort;
import com.alphalabs.connectify.ref.archsample.member.application.port.out.input.ArchSampleInsertDto;
import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class ArchSamplePersistenceAdapter implements ArchSampleInsertPort, ArchSampleGetPort {

	private final ArchSampleRepository sampleRepository;
	private final ArchSamplePersistenceMapper samplePersistenceMapper;

	@Override
	public Long insertMember(ArchSampleInsertDto insertDto) {

		ArchSampleJpaEntity jpaEntity = samplePersistenceMapper.mapToJpaEntity(insertDto);

		return sampleRepository.save(jpaEntity).getId();
	}

	@Override
	public Optional<ArchSampleDomain> getMember(Long id) {

		Optional<ArchSampleJpaEntity> jpaEntity = sampleRepository.findById(id);

		return samplePersistenceMapper.mapToDomainEntity(jpaEntity);
	}
}
