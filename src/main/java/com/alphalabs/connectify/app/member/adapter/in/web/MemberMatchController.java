package com.alphalabs.connectify.app.member.adapter.in.web;

import com.alphalabs.connectify.app.member.application.port.in.GetProfileUseCase;
import com.alphalabs.connectify.app.member.domain.MemberDistanceDomain;
import com.alphalabs.connectify.common.ListDto;
import com.alphalabs.connectify.common.ResultDto;
import com.alphalabs.connectify.common.architecture.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class MemberMatchController {

	private final GetProfileUseCase getUseCase;

	@GetMapping(path = "/match/nearby")
	ResponseEntity<ResultDto<ListDto<MemberDistanceDomain>>> nearByMembers(@RequestHeader("Authorization") String authorization) {

		String accessToken = authorization.split(" ")[1];

		List<MemberDistanceDomain> nearbyMembers = getUseCase.getNearbyMembers(accessToken, 10L);

		ListDto<MemberDistanceDomain> listDto = new ListDto<>(nearbyMembers, nearbyMembers.size(), 0);

		ResultDto<ListDto<MemberDistanceDomain>> result = new ResultDto<>(200, "테스트 메시지", listDto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
