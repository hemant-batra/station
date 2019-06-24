package com.abg.stationMapping.response.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class ErrorStatus {

	private final String request_time;
	private final int request_errors;
	@Singular private final List<ErrorDetails> errors;

	
}
