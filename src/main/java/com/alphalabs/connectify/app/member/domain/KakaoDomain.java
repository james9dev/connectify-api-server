package com.alphalabs.connectify.app.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoDomain {

	private String access_token;

	private final Long id;
	private final String connected_at;
	private final KakaoAccount kakao_account;
}

