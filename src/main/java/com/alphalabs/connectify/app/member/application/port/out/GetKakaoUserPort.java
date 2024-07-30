package com.alphalabs.connectify.app.member.application.port.out;

import com.alphalabs.connectify.app.member.domain.KakaoDomain;

import java.util.Optional;

public interface GetKakaoUserPort {

	Optional<KakaoDomain> getUser(String accessToken);
}
