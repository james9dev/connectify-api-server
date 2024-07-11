package com.alphalabs.connectify.common;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class ResultDto<T> {

	private final Integer resultCode;
	private final String message;
	private final T data;

}
