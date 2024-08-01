package com.alphalabs.connectify.app.member.domain;

import com.alphalabs.connectify.app.member.domain.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
@Getter
public class ProfileDomain {

	private Long id;

	private final String nickName;
	private final GenderType gender;
	private final Integer height;
	private final String birthyear;
	private final String birthday;

	private final Double latitude;
	private final Double longitude;

}
