package com.alphalabs.connectify.member.member.application.service;

import com.alphalabs.connectify.exception.*;
import com.alphalabs.connectify.member.member.application.port.in.AuthUseCase;
import com.alphalabs.connectify.member.member.application.port.in.GetMemberUseCase;
import com.alphalabs.connectify.member.member.application.port.in.RegisterKakaoUseCase;
import com.alphalabs.connectify.member.member.application.port.in.command.RefreshAuthCommand;
import com.alphalabs.connectify.member.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.member.member.application.port.out.GetMemberPort;
import com.alphalabs.connectify.member.member.application.port.out.GetKakaoUserPort;
import com.alphalabs.connectify.member.member.application.port.out.InsertMemberPort;
import com.alphalabs.connectify.member.member.domain.AuthDomain;
import com.alphalabs.connectify.member.member.domain.KakaoDomain;
import com.alphalabs.connectify.member.member.domain.MemberDomain;
import com.alphalabs.connectify.common.architecture.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class MemberRegisterService implements RegisterKakaoUseCase, AuthUseCase, GetMemberUseCase {

	private final InsertMemberPort insertMemberPort;
	private final GetMemberPort getMemberPort;
	private final GetKakaoUserPort getKakaoUserPort;

	@Override
	public MemberDomain getMember(Long id) throws NoSuchElementFoundException {
		return getMemberPort.getMember(id).orElseThrow(() ->
				new NoSuchElementFoundException("Member not found")
		);
	}

	@Override
	public AuthDomain authKakao(RegisterKakaoCommand command) {

		Optional<MemberDomain> member = getMemberPort.getMember(command.getKakaoAccessToken());

		Long memberNo;

		if (member.isPresent()) {
			memberNo = member.get().getNo();
		} else {
			KakaoDomain kakaoDomain = getKakaoUserPort.getUser(command.getKakaoAccessToken()).orElseThrow();
			kakaoDomain.setAccess_token(command.getKakaoAccessToken());

			memberNo = insertMemberPort.insertKakaoUser(kakaoDomain);
		}

		return AuthDomain.withMemberNo(memberNo);
	}

	@Override
	public AuthDomain refreshJwt(RefreshAuthCommand command) throws NoSuchElementFoundException {

		String refreshToken = command.getRefreshToken();

		return AuthDomain.refreshJwt(refreshToken);
	}

}
