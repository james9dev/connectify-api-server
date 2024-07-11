package com.alphalabs.connectify.ref.archsample.member.adapter.in.web;

import org.springframework.stereotype.Component;


@Component
public class ArchSampleResponseMapper {

    static ArchSampleResponseDto mapToRegiterResponse(Long id) {
		return new ArchSampleResponseDto(id);
    }
}
