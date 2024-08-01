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

	@Data
	@AllArgsConstructor
	public static class KakaoAccount {
		private final String email; //카카오계정 대표 이메일
		private final String name; //카카오계정 이름
		private final String gender; //female: 여성, male: 남성
		private final String birthyear; //출생 연도(YYYY 형식)
		private final String birthday; //생일(MMDD 형식)
		private final String birthday_type; //생일 타입: SOLAR(양력) 또는 LUNAR(음력)
		private final String phone_number; //카카오계정의 전화번호, 국내 번호인 경우 +82 00-0000-0000 형식, 해외 번호인 경우 자릿수, 붙임표(-) 유무나 위치가 다를 수 있음, (참고: libphonenumber)

		private final Boolean is_email_valid; //이메일 유효 여부, true: 유효한 이메일, false: 이메일이 다른 카카오계정에 사용돼 만료
		private final Boolean is_email_verified; //이메일 인증 여부, true: 인증된 이메일, false: 인증되지 않은 이메일
	}

}

