package com.alphalabs.connectify.app.member.adapter.in.web;

import com.alphalabs.connectify.app.member.application.port.in.AuthUseCase;
import com.alphalabs.connectify.app.member.application.port.in.command.RefreshAuthCommand;
import com.alphalabs.connectify.app.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.app.member.application.port.in.RegisterKakaoUseCase;
import com.alphalabs.connectify.app.member.domain.AuthDomain;
import com.alphalabs.connectify.common.ResultDto;
import com.alphalabs.connectify.common.architecture.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class MemberSignController {

	private final RegisterKakaoUseCase registerKakaoAuthUseCase;
	private final AuthUseCase authUseCase;

	@PostMapping(path = "/member/sign/kakao")
	ResponseEntity<ResultDto<AuthDomain>> authKakao(@RequestBody MemberSignUpKakaoRequestDto request) {

		String kakaoAccessToken = request.getAccessToken();

		RegisterKakaoCommand command = new RegisterKakaoCommand(kakaoAccessToken);

		AuthDomain authDomain = registerKakaoAuthUseCase.authKakao(command);

		ResultDto<AuthDomain> result = new ResultDto<>(201, "테스트 메시지", authDomain);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PostMapping(path = "/member/refresh-token")
	ResponseEntity<ResultDto<AuthDomain>> refreshToken(@RequestHeader("Authorization") String authorization) {

		String refreshToken = authorization.split(" ")[1];

		RefreshAuthCommand command = new RefreshAuthCommand(refreshToken);

		AuthDomain authDomain = authUseCase.refreshJwt(command);

		ResultDto<AuthDomain> result = new ResultDto<>(201, "테스트 메시지", authDomain);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
