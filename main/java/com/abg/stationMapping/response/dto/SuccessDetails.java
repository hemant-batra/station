package com.abg.stationMapping.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessDetails {

	private final String code;
	private final String message;
	private final String details;

}
