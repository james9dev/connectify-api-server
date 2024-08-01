package com.alphalabs.connectify.app.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * Domain entity 임을 명확하게 하기 위해 suffix로 'Domain'을 사용한다.
 */

@Value
@AllArgsConstructor
@Getter
public class MemberDomain {

	private Long no;
	private final String email;
	private final String name;
	private final String phoneNumber;

	private final ProfileDomain profile;


	/**
	 * Creates an {@link MemberDomain} entity without an ID. Use to create a new entity that is not yet
	 * persisted.
	 */
	public static MemberDomain withoutNo(String email,
										 String name,
										 ProfileDomain profile) {

		return new MemberDomain(null, email, name, null, profile);
	}

	/**
	 * Creates an {@link MemberDomain} entity with an ID. Use to reconstitute a persisted entity.
	 */
	public static MemberDomain withNo(Long no,
									  String email,
									  String name,
									  ProfileDomain profile) {

		return new MemberDomain(no, email, name, null, profile);
	}

}
