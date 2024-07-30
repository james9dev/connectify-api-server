package com.alphalabs.connectify.common.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

	private static SecretKey secretKey;

	public enum TOKEN_TYPE {
		ACCESS_TOKEN, REFRESH_TOKEN
	}

	//private final long defaultExpiration = 2592000000L; //86400 * 30 * 1000;
	//long expirationTime = expiredMs == null ? (System.currentTimeMillis() + defaultExpiration) : expiredMs;

	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
	}

	public static Long getId(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("id", Long.class);
	}

	public static TOKEN_TYPE getType(String token) {

		String type = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("type", String.class);

		return TOKEN_TYPE.valueOf(type);
	}

	/*
	public String getRole(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
	}
	*/

	public static Boolean isExpired(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}

	public static String createAccessToken(Long memberNo, Long expiredMs) {

		return createJwt(memberNo, null, expiredMs, TOKEN_TYPE.ACCESS_TOKEN.toString());
	}

	public static String createRefreshToken(Long memberNo, Long expiredMs) {

		return createJwt(memberNo, null, expiredMs, TOKEN_TYPE.REFRESH_TOKEN.toString());
	}

	public static String createJwt(Long id, String role, Long expiredMs, String tokenType) {

		return Jwts.builder()
				.claim("id", id)
				.claim("type", tokenType)
				.claim("role", role)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiredMs))
				.signWith(secretKey)
				.compact();
	}
}