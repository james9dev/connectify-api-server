package com.alphalabs.connectify.app.member.domain;

import com.alphalabs.connectify.app.member.domain.enums.GenderType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
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

	private List<ProfilePicture> pictures;

	public ProfileDomain(Long id, String nickName, GenderType gender, Integer height, String birthyear, String birthday, Double latitude, Double longitude, List<ProfilePicture> pictures) {
		this.id = id;
		this.nickName = nickName;
		this.gender = gender;
		this.height = height;
		this.birthyear = birthyear;
		this.birthday = birthday;
		this.latitude = latitude;
		this.longitude = longitude;
		this.pictures = pictures;
	}

	public ProfileDomain(String nickName, GenderType gender, Integer height, String birthyear, String birthday, Double latitude, Double longitude) {
		this.nickName = nickName;
		this.gender = gender;
		this.height = height;
		this.birthyear = birthyear;
		this.birthday = birthday;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Data
	@AllArgsConstructor
	public static class ProfilePicture {
		private Long id;
		private String imageUrl;
		private Integer order;
		private LocalDateTime createdAt;
	}
}
