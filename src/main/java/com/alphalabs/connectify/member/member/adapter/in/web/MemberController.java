package com.alphalabs.connectify.member.member.adapter.in.web;

import com.alphalabs.connectify.member.member.application.port.in.AuthUseCase;
import com.alphalabs.connectify.member.member.application.port.in.GetMemberUseCase;
import com.alphalabs.connectify.member.member.application.port.in.RegisterKakaoUseCase;
import com.alphalabs.connectify.member.member.application.port.in.command.RefreshAuthCommand;
import com.alphalabs.connectify.member.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.member.member.domain.AuthDomain;
import com.alphalabs.connectify.member.member.domain.MemberDomain;
import com.alphalabs.connectify.common.ResultDto;
import com.alphalabs.connectify.common.architecture.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class MemberController {

	private final RegisterKakaoUseCase registerKakaoAuthService;
	private final AuthUseCase authUseCase;

	private final GetMemberUseCase getUseCase;

	@PostMapping(path = "/member/auth/kakao")
	ResponseEntity<ResultDto<MemberAuthResponseDto>> authKakao(@RequestBody MemberSignUpKakaoRequestDto request) {

		String kakaoAccessToken = request.getAccessToken();

		RegisterKakaoCommand command = new RegisterKakaoCommand(kakaoAccessToken);

		AuthDomain authDomain = registerKakaoAuthService.authKakao(command);

		MemberAuthResponseDto responseDto = MemberMapper.mapToMemberAuthResponse(authDomain);

		ResultDto<MemberAuthResponseDto> result = new ResultDto<>(201, "테스트 메시지", responseDto);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PostMapping(path = "/member/refresh-token")
	ResponseEntity<ResultDto<MemberAuthResponseDto>> refreshToken(@RequestHeader("Authorization") String authorization) {

		String refreshToken = authorization.split(" ")[1];

		RefreshAuthCommand command = new RefreshAuthCommand(refreshToken);

		AuthDomain authDomain = authUseCase.refreshJwt(command);

		MemberAuthResponseDto responseDto = MemberMapper.mapToMemberAuthResponse(authDomain);

		ResultDto<MemberAuthResponseDto> result = new ResultDto<>(201, "테스트 메시지", responseDto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(path = "/member/info/{id}")
	ResponseEntity<ResultDto<MemberResponseDto>> getMember(@PathVariable Long id) {

		MemberDomain memberDomain = getUseCase.getMember(id);

		MemberResponseDto responseDto = MemberMapper.mapToMemberResponse(memberDomain);

		ResultDto<MemberResponseDto> result = new ResultDto<>(200, "테스트 메시지", responseDto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
