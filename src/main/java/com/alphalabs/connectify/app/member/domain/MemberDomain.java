package com.alphalabs.connectify.app.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Domain entity 임을 명확하게 하기 위해 suffix로 'Domain'을 사용한다.
 */

@Value
@Getter
public class MemberDomain {

	private Long id;
	private final String email;
	private final String name;
	private final String phoneNumber;

	private final LocalDateTime createdAt;

	private final Boolean newbie;
	private final ProfileDomain profile;

	public MemberDomain(Long id, String email, String name, String phoneNumber, LocalDateTime createdAt, ProfileDomain profile) {

		this.id = id;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.profile = profile;
		
		this.newbie = createdAt.isAfter(LocalDateTime.now().minusDays(30));;
	}

	/**
	 * Creates an {@link MemberDomain} entity with an ID. Use to reconstitute a persisted entity.
	 */
	public static MemberDomain withId(Long id,
									  String email,
									  String name,
									  LocalDateTime createdAt,
									  ProfileDomain profile) {

		return new MemberDomain(id, email, name, null, createdAt, profile);
	}

}
