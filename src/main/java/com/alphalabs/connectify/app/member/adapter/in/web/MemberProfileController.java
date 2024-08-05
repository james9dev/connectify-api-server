package com.alphalabs.connectify.app.member.adapter.in.web;

import com.alphalabs.connectify.app.member.application.port.in.GetProfileUseCase;
import com.alphalabs.connectify.app.member.application.port.in.UpdateProfileUseCase;
import com.alphalabs.connectify.app.member.application.port.in.command.UpdateProfileCommand;
import com.alphalabs.connectify.app.member.domain.MemberDomain;
import com.alphalabs.connectify.app.member.domain.ProfileDomain;
import com.alphalabs.connectify.common.ResultDto;
import com.alphalabs.connectify.common.architecture.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class MemberProfileController {

	private final GetProfileUseCase getUseCase;
	private final UpdateProfileUseCase updateProfileUseCase;


	@GetMapping(path = "/profile/{memberId}")
	ResponseEntity<ResultDto<MemberDomain>> getProfile(@PathVariable Long memberId) {

		MemberDomain member = getUseCase.getProfile(memberId);

		ResultDto<MemberDomain> result = new ResultDto<>(200, "테스트 메시지", member);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(path = "/profile/me")
	ResponseEntity<ResultDto<MemberDomain>> getProfileMe(@RequestHeader("Authorization") String authorization) {

		String accessToken = authorization.split(" ")[1];

		MemberDomain member = getUseCase.getProfileMe(accessToken);

		ResultDto<MemberDomain> result = new ResultDto<>(200, "테스트 메시지", member);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping(path = "/profile/update")
	ResponseEntity<ResultDto<ProfileDomain>> updateProfile(@RequestHeader("Authorization") String authorization,
														   @RequestBody RequestProfileUpdateDto profileRequest) {

		String accessToken = authorization.split(" ")[1];

		UpdateProfileCommand updateProfileCommand = new UpdateProfileCommand(
				accessToken,
				profileRequest.getNickName(),
				profileRequest.getGender(),
				profileRequest.getHeight(),
				profileRequest.getBirthyear(),
				profileRequest.getBirthday(),
				profileRequest.getLatitude(),
				profileRequest.getLongitude()
				);

		ProfileDomain profileDomain = updateProfileUseCase.updateProfile(updateProfileCommand);


		ResultDto<ProfileDomain> result = new ResultDto<>(200, "테스트 메시지", profileDomain);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
