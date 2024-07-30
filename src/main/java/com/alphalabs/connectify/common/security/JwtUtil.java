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

	private static String PAYLOAD_MEMBER_NO = "memberNo";

	//private final long defaultExpiration = 2592000000L; //86400 * 30 * 1000;
	//long expirationTime = expiredMs == null ? (System.currentTimeMillis() + defaultExpiration) : expiredMs;

	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
	}

	public static Long getLoginId(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get(PAYLOAD_MEMBER_NO, Long.class);
	}

	/*
	public String getRole(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
	}
	*/

	public static Boolean isExpired(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}

	public static String createJwt(Long memberNo, Long expiredMs) {

		return createJwt(memberNo, null, expiredMs);
	}

	public static String createJwt(Long loginId, String role, Long expiredMs) {

		return Jwts.builder()
				.claim(PAYLOAD_MEMBER_NO, loginId)
				.claim("role", role)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiredMs))
				.signWith(secretKey)
				.compact();
	}
}