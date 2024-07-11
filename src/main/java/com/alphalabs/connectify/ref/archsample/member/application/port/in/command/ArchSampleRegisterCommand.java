package com.alphalabs.connectify.ref.archsample.member.application.port.in.command;

import com.alphalabs.connectify.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ArchSampleRegisterCommand extends SelfValidating<ArchSampleRegisterCommand> {

	@NotEmpty(message = "이메일 입력은 필수 입니다.")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
			flags = Pattern.Flag.CASE_INSENSITIVE)
	private final String email;

	@NotEmpty(message = "비밀번호 입력은 필수 입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private final String password;

	@NotEmpty(message = "이름 입력은 필수 입니다.")
	private final String name;

	public ArchSampleRegisterCommand(String email,
									 String password,
									 String name) {

		this.email = email;
		this.password = password;
		this.name = name;

		this.validateSelf();
	}

}
