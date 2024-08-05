package com.alphalabs.connectify.app.member.application.service;

import com.alphalabs.connectify.app.member.application.port.in.AuthUseCase;
import com.alphalabs.connectify.app.member.application.port.in.RegisterKakaoUseCase;
import com.alphalabs.connectify.app.member.application.port.in.command.RefreshAuthCommand;
import com.alphalabs.connectify.app.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.app.member.application.port.out.GetKakaoUserPort;
import com.alphalabs.connectify.app.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.app.member.application.port.out.InsertMemberPort;
import com.alphalabs.connectify.app.member.domain.AuthDomain;
import com.alphalabs.connectify.app.member.domain.KakaoDomain;
import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.exception.*;
import com.alphalabs.connectify.common.architecture.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class MemberRegisterService implements RegisterKakaoUseCase, AuthUseCase {

	private final InsertMemberPort insertMemberPort;
	private final GetMemberPort getMemberPort;
	private final GetKakaoUserPort getKakaoUserPort;



	@Override
	public AuthDomain authKakao(RegisterKakaoCommand command) {

		Optional<MemberDomain> member = getMemberPort.getMember(command.getKakaoAccessToken());

		Long memberId;

		if (member.isPresent()) {
			memberId = member.get().getId();
		} else {
			KakaoDomain kakaoDomain = getKakaoUserPort.getUser(command.getKakaoAccessToken()).orElseThrow();
			kakaoDomain.setAccess_token(command.getKakaoAccessToken());

			memberId = insertMemberPort.insertKakaoUser(kakaoDomain);
		}

		return AuthDomain.withMemberId(memberId);
	}

	@Override
	public AuthDomain refreshJwt(RefreshAuthCommand command) throws NoSuchElementFoundException {

		String refreshToken = command.getRefreshToken();

		return AuthDomain.refreshJwt(refreshToken);
	}

}
