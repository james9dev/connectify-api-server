package com.alphalabs.connectify.app.member.adapter.out.external;

import com.alphalabs.connectify.common.architecture.PersistenceAdapter;
import com.alphalabs.connectify.app.member.application.port.out.GetKakaoUserPort;
import com.alphalabs.connectify.app.member.domain.KakaoDomain;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
public class KakaoAuthAdapter implements GetKakaoUserPort {

	private final HttpKakaoAuthClient kakaoAuthClient;

	@Override
	public Optional<KakaoDomain> getUser(String accessToken) {

		Optional<KakaoDomain> kakaoDomain = kakaoAuthClient.auth("Bearer " + accessToken);

		return kakaoDomain;
	}
}
