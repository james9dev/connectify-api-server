package com.alphalabs.connectify.member.member.adapter.out.external;

import com.alphalabs.connectify.member.member.domain.KakaoDomain;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Optional;

public interface KakaoAuthClient {

	@PostExchange("/v2/user/me")
	Optional<KakaoDomain> auth(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}

