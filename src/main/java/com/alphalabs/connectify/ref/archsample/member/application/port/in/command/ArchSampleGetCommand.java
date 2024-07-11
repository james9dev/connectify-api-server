package com.alphalabs.connectify.ref.archsample.member.application.port.in.command;

import com.alphalabs.connectify.common.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ArchSampleGetCommand extends SelfValidating<ArchSampleGetCommand> {

	@NotEmpty(message = "ID 값은 필수 입니다.")
	private final Long id;

	public ArchSampleGetCommand(Long id) {

		this.id = id;

		this.validateSelf();
	}

}
