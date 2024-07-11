package com.alphalabs.connectify.ref.archsample.member.application.service;

import com.alphalabs.connectify.common.UseCase;
import com.alphalabs.connectify.ref.archsample.member.application.port.in.ArchSampleRegisterUseCase;
import com.alphalabs.connectify.ref.archsample.member.application.port.in.ArchSampleGetUseCase;
import com.alphalabs.connectify.ref.archsample.member.application.port.in.command.ArchSampleRegisterCommand;
import com.alphalabs.connectify.ref.archsample.member.application.port.out.ArchSampleInsertPort;
import com.alphalabs.connectify.ref.archsample.member.application.port.out.ArchSampleGetPort;
import com.alphalabs.connectify.ref.archsample.member.application.port.out.input.ArchSampleInsertDto;
import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
@Transactional
public class ArchSampleService implements ArchSampleRegisterUseCase, ArchSampleGetUseCase {

	private final ArchSampleInsertPort insertPort;
	private final ArchSampleGetPort getPort;

	@Override
	public Long register(ArchSampleRegisterCommand command) {

		ArchSampleDomain sampleDomain = ArchSampleDomain.withoutId(command.getEmail(), command.getPassword(), command.getName());

		/*
		비즈니스 로직 처리
		{
			단순 코드 블럭
		 }
		*/

		/*
		sampleDomain.logicFunction()
		 */

		ArchSampleInsertDto insertDto = ArchSampleInsertDto.toInsertDto(sampleDomain.getEmail(), sampleDomain.getPassword(), sampleDomain.getName());

		return insertPort.insertMember(insertDto);
	}

	@Override
	public ArchSampleDomain getMember(Long id) {
		return getPort.getMember(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
	}
}
