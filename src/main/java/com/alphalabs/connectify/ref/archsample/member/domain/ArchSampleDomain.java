package com.alphalabs.connectify.ref.archsample.member.domain;

import lombok.*;

/**
 * Domain entity 임을 명확하게 하기 위해 suffix로 'Domain'을 사용한다.
 */

@Value
@AllArgsConstructor
public class ArchSampleDomain {

	/**
	 * The unique ID of the member.
	 */
	@Getter
	private Long id;

	@Getter
	@NonNull
	private final String email;

	@Getter
	@NonNull
	private final String password;

	@Getter
	@NonNull
	private final String name;


	/**
	 * Creates an {@link ArchSampleDomain} entity without an ID. Use to create a new entity that is not yet
	 * persisted.
	 */
	public static ArchSampleDomain withoutId(String email,
											 String password,
											 String name) {

		return new ArchSampleDomain(null, email, password, name);
	}

	/**
	 * Creates an {@link ArchSampleDomain} entity with an ID. Use to reconstitute a persisted entity.
	 */
	public static ArchSampleDomain withId(Long id,
										  String email,
										  String password,
										  String name) {

		return new ArchSampleDomain(id, email, password, name);
	}

}
