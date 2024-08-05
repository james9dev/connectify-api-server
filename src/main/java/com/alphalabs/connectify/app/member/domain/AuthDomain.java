package com.alphalabs.connectify.app.member.domain;

import com.alphalabs.connectify.common.AuthTokenDto;
import com.alphalabs.connectify.common.security.JwtUtil;
import com.alphalabs.connectify.exception.UnMatchTokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
public class AuthDomain {

	private static final Long ACCESS_EXP = 86400000L;
	private static final Long REFRESH_EXP = 2592000000L;

	@Getter
	AuthTokenDto authToken;

	public static AuthDomain withMemberId(Long memberId) {

		long accessExpirationTime = System.currentTimeMillis() + ACCESS_EXP;
		long refreshExpirationTime = System.currentTimeMillis() + REFRESH_EXP;

		String accessToken = JwtUtil.createAccessToken(memberId, accessExpirationTime);
		String refreshToken = JwtUtil.createRefreshToken(memberId, refreshExpirationTime);


		AuthTokenDto authTokenDto = new AuthTokenDto(accessToken, refreshToken);

		return new AuthDomain(authTokenDto);
	}

	public static AuthDomain refreshJwt(String refreshToken) throws UnMatchTokenType {

		if (JwtUtil.getType(refreshToken) != JwtUtil.TOKEN_TYPE.REFRESH_TOKEN) {
			throw new UnMatchTokenType("Invalid token");
		}

		Long memberId = JwtUtil.getId(refreshToken);

		return withMemberId(memberId);
	}
}
