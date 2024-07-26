package com.alphalabs.connectify.member.member.adapter.in.web;

import com.alphalabs.connectify.member.member.application.port.in.GetMemberUseCase;
import com.alphalabs.connectify.member.member.application.port.in.RegisterKakaoUseCase;
import com.alphalabs.connectify.member.member.application.port.in.command.RegisterKakaoCommand;
import com.alphalabs.connectify.member.member.domain.MemberDomain;
import com.alphalabs.connectify.common.ResultDto;
import com.alphalabs.connectify.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class MemberController {

	private final RegisterKakaoUseCase registerKakaoAuthService;

	private final GetMemberUseCase getUseCase;

	@PostMapping(path = "/member/auth/kakao")
	ResponseEntity<ResultDto<MemberSignUpResponseDto>> authKakao(@RequestBody MemberSignUpKakaoRequestDto request) {

		String accessToken = request.getAccessToken();

		RegisterKakaoCommand command = new RegisterKakaoCommand(accessToken);

		Long id = registerKakaoAuthService.authKakao(command);

		MemberSignUpResponseDto responseDto = MemberMapper.mapToRegisterResponse(id);

		ResultDto<MemberSignUpResponseDto> result = new ResultDto<>(201, "테스트 메시지", responseDto);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping(path = "/member/{id}")
	ResponseEntity<ResultDto<MemberResponseDto>> getMember(@PathVariable Long id) {

		MemberDomain memberDomain = getUseCase.getMember(id);

		MemberResponseDto responseDto = MemberMapper.mapToMemberResponse(memberDomain);

		ResultDto<MemberResponseDto> result = new ResultDto<>(200, "테스트 메시지", responseDto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
