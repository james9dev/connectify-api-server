package com.alphalabs.connectify.member.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

/**
 * Domain entity 임을 명확하게 하기 위해 suffix로 'Domain'을 사용한다.
 */

@Value
@AllArgsConstructor
public class MemberDomain {

	/**
	 * The unique ID of the member.
	 */
	@Getter
	private Long id;

	@Getter
	private final String email;

	@Getter
	private final String name;


	/**
	 * Creates an {@link MemberDomain} entity without an ID. Use to create a new entity that is not yet
	 * persisted.
	 */
	public static MemberDomain withoutId(String email,
										 String name) {

		return new MemberDomain(null, email, name);
	}

	/**
	 * Creates an {@link MemberDomain} entity with an ID. Use to reconstitute a persisted entity.
	 */
	public static MemberDomain withId(Long id,
									  String email,
									  String name) {

		return new MemberDomain(id, email, name);
	}

}
