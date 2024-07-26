package com.alphalabs.connectify.member.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class KakaoDomain {

	private String access_token;

	private final Long id;
	private final String connected_at;
	private final KakaoAccount kakao_account;
}

