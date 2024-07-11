package com.alphalabs.connectify.ref.archsample.member.adapter.in.web;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
class ArchSampleRequestDto {

	private final String email;
	private final String password;
	private final String name;

}
