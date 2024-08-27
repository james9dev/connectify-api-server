package com.alphalabs.connectify.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ListDto<T> {

	private final List<T> values;
	private final Integer size;
	private final Integer total;

}
