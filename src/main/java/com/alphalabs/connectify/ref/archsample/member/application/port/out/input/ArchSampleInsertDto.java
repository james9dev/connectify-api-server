package com.alphalabs.connectify.ref.archsample.member.application.port.out.input;

import com.alphalabs.connectify.ref.archsample.member.domain.ArchSampleDomain;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArchSampleInsertDto {

	private final String email;
	private final String password;
	private final String name;

	public static ArchSampleInsertDto toInsertDto(String email,
											 String password,
											 String name) {

		return new ArchSampleInsertDto(email, password, name);
	}
}
