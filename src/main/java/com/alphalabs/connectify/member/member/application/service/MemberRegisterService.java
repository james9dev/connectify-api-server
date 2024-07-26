package com.alphalabs.connectify.member.member.application.service;

import com.alphalabs.connectify.member.member.application.port.in.GetMemberUseCase;
import com.alphalabs.connectify.member.member.application.port.in.RegisterKakaoUseCase;
import com.alphalabs.connectify.member.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.member.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.member.member.application.port.out.GetKakaoUserPort;
import com.alphalabs.connectify.member.member.application.port.out.InsertMemberPort;
import com.alphalabs.connectify.member.member.domain.KakaoDomain;
import com.alphalabs.connectify.member.member.domain.MemberDomain;
import com.alphalabs.connectify.common.architecture.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class MemberRegisterService implements RegisterKakaoUseCase, GetMemberUseCase {

	private final InsertMemberPort insertMemberPort;
	private final GetMemberPort getMemberPort;
	private final GetKakaoUserPort getKakaoUserPort;

	@Override
	public MemberDomain getMember(Long id) {
		return getMemberPort.getMember(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
	}

	@Override
	public Long authKakao(RegisterKakaoCommand command) {

		Optional<MemberDomain> member = getMemberPort.getMember(command.getAccessToken());

		if (member.isPresent()) {
			return member.get().getId();
		}

		KakaoDomain kakaoDomain = getKakaoUserPort.getUser(command.getAccessToken()).orElseThrow();
		kakaoDomain.setAccess_token(command.getAccessToken());

		return insertMemberPort.insertKakaoUser(kakaoDomain);
	}

}
