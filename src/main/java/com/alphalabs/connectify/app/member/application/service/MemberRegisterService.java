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
import com.alphalabs.connectify.common.AuthTokenDto;
import com.alphalabs.connectify.exception.*;
import com.alphalabs.connectify.common.architecture.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@UseCase
@Transactional
public class MemberRegisterService implements RegisterKakaoUseCase, AuthUseCase {

	private final InsertMemberPort insertMemberPort;
	private final GetMemberPort getMemberPort;
	private final GetKakaoUserPort getKakaoUserPort;

	@Override
	public AuthDomain testAuthKakao(RegisterKakaoCommand command) {

		KakaoDomain kakaoDomain = getKakaoUserPort.getUser(command.getKakaoAccessToken()).orElseThrow();

//		Optional<MemberDomain> member = getMemberPort.getMemberByProvider(kakaoDomain.getId());
//
		AuthTokenDto.AuthType authType = null;
		Long memberId;
//
//		if (member.isPresent()) {
//			authType = AuthTokenDto.AuthType.SignIn;
//			memberId = member.get().getId();
//		} else {


		for (int i = 0; i < 10; i++){
			kakaoDomain.setId(new Random().nextLong());
			kakaoDomain.setAccess_token(command.getKakaoAccessToken());

			authType = AuthTokenDto.AuthType.SignUp;
			memberId = insertMemberPort.insertKakaoUser(kakaoDomain);
		}
//		}

		return null;
	}


	@Override
	public AuthDomain authKakao(RegisterKakaoCommand command) {

		KakaoDomain kakaoDomain = getKakaoUserPort.getUser(command.getKakaoAccessToken()).orElseThrow();

		Optional<MemberDomain> member = getMemberPort.getMemberByProvider(kakaoDomain.getId());

		AuthTokenDto.AuthType authType;
		Long memberId;

		if (member.isPresent()) {
			authType = AuthTokenDto.AuthType.SignIn;
			memberId = member.get().getId();
		} else {

			kakaoDomain.setAccess_token(command.getKakaoAccessToken());

			authType = AuthTokenDto.AuthType.SignUp;
			memberId = insertMemberPort.insertKakaoUser(kakaoDomain);
		}

		return AuthDomain.withMemberId(authType, memberId);
	}

	@Override
	public AuthDomain refreshJwt(RefreshAuthCommand command) throws NoSuchElementFoundException {

		String refreshToken = command.getRefreshToken();

		return AuthDomain.refreshJwt(refreshToken);
	}

}
