package com.alphalabs.connectify.ref.archsample.member.adapter.in.web;

import com.alphalabs.connectify.common.ResultDto;
import com.alphalabs.connectify.common.WebAdapter;
import com.alphalabs.connectify.ref.archsample.member.application.port.in.ArchSampleGetUseCase;
import com.alphalabs.connectify.ref.archsample.member.application.port.in.ArchSampleRegisterUseCase;
import com.alphalabs.connectify.ref.archsample.member.application.port.in.command.ArchSampleRegisterCommand;
import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ArchSampleController {

	private final ArchSampleRegisterUseCase archSample1UseCase;
	private final ArchSampleGetUseCase getUseCase;

	@PostMapping(path = "/archsample/member/register")
	ResponseEntity<ResultDto<ArchSampleResponseDto>> registerMember(@RequestBody ArchSampleRequestDto request) {

		ArchSampleRegisterCommand command = new ArchSampleRegisterCommand(
				request.getEmail(),
				request.getPassword(),
				request.getName());


		Long id = archSample1UseCase.register(command);

		ArchSampleResponseDto responseDto = ArchSampleResponseMapper.mapToRegiterResponse(id);

		ResultDto<ArchSampleResponseDto> result = new ResultDto<>(201, "테스트 메시지", responseDto);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping(path = "/archsample/member/{id}}")
	ResponseEntity<ResultDto<ArchSampleResponseDto>> getMember(@PathVariable Long id) {

		ArchSampleDomain memberDomain = getUseCase.getMember(id);

		ArchSampleResponseDto responseDto = ArchSampleResponseMapper.mapToRegiterResponse(memberDomain.getId());

		ResultDto<ArchSampleResponseDto> result = new ResultDto<>(200, "테스트 메시지", responseDto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
