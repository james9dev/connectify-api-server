package com.alphalabs.connectify.member.member.application.port.out;

import com.alphalabs.connectify.member.member.domain.KakaoDomain;

import java.util.Optional;

public interface GetKakaoUserPort {

	Optional<KakaoDomain> getUser(String accessToken);
}
