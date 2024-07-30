package com.alphalabs.connectify.member.member.domain;

import com.alphalabs.connectify.common.AuthTokenDto;
import com.alphalabs.connectify.common.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
public class AuthDomain {

	private static final Long ACCESS_EXP = 86400000L;
	private static final Long REFRESH_EXP = 2592000000L;

	@Getter
	AuthTokenDto authTokenDto;

	public static AuthDomain withMemberNo(Long memberNo) {

		long accessExpirationTime = System.currentTimeMillis() + ACCESS_EXP;
		long refreshExpirationTime = System.currentTimeMillis() + REFRESH_EXP;

		String accessToken = JwtUtil.createJwt(memberNo, accessExpirationTime);
		String refreshToken = JwtUtil.createJwt(memberNo, refreshExpirationTime);


		AuthTokenDto authTokenDto = new AuthTokenDto(accessToken, refreshToken);

		return new AuthDomain(authTokenDto);
	}

	public static AuthDomain refreshJwt(String refreshToken) {

		Long memberNo = JwtUtil.getLoginId(refreshToken);

		return withMemberNo(memberNo);
	}
}
