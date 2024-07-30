package com.alphalabs.connectify.app.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
	private Long no;

	@Getter
	private final String email;

	@Getter
	private final String name;


	/**
	 * Creates an {@link MemberDomain} entity without an ID. Use to create a new entity that is not yet
	 * persisted.
	 */
	public static MemberDomain withoutNo(String email,
										 String name) {

		return new MemberDomain(null, email, name);
	}

	/**
	 * Creates an {@link MemberDomain} entity with an ID. Use to reconstitute a persisted entity.
	 */
	public static MemberDomain withNo(Long no,
									  String email,
									  String name) {

		return new MemberDomain(no, email, name);
	}

}
