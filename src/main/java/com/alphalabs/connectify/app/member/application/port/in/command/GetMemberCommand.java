package com.alphalabs.connectify.app.member.application.port.in.command;

import com.alphalabs.connectify.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class GetMemberCommand extends SelfValidating<GetMemberCommand> {

	@NotEmpty(message = "ID 값은 필수 입니다.")
	private final Long id;

	public GetMemberCommand(Long id) {

		this.id = id;

		this.validateSelf();
	}

}
