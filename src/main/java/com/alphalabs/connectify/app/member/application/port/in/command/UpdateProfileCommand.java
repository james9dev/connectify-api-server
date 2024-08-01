package com.alphalabs.connectify.app.member.application.port.in.command;

import com.alphalabs.connectify.app.member.domain.enums.GenderType;
import com.alphalabs.connectify.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class UpdateProfileCommand extends SelfValidating<UpdateProfileCommand> {

	private final String accessToken;

	private final String nickName;
	private final GenderType gender;
	private final Integer height;
	private final String birthyear;
	private final String birthday;

	private final Double latitude;
	private final Double longitude;

	public UpdateProfileCommand(String accessToken, String nickName, GenderType gender, Integer height, String birthyear, String birthday, double latitude, double longitude) {
		this.accessToken = accessToken;
		this.nickName = nickName;
		this.gender = gender;
		this.height = height;
		this.birthyear = birthyear;
		this.birthday = birthday;
		this.latitude = latitude;
		this.longitude = longitude;

		this.validateSelf();
	}
}
