package com.abg.stationMapping.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
	private final ErrorStatus status;

}
