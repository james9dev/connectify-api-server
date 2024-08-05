package com.alphalabs.connectify.app.member.application.port.in.command;

import com.alphalabs.connectify.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@EqualsAndHashCode(callSuper = false)
public class UploadPictureCommand extends SelfValidating<UpdateProfileCommand> {

	private final String accessToken;

	@NotNull(message = "파일이 없습니다.")
	private final MultipartFile multipartFile;

	@NotNull(message = "Order값이 없습니다.")
	private final Integer order;

	public UploadPictureCommand(String accessToken, MultipartFile multipartFile, Integer order) {
		this.accessToken = accessToken;
		this.multipartFile = multipartFile;
		this.order = order;

		this.validateSelf();
	}
}
