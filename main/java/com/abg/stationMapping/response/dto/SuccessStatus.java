package com.abg.stationMapping.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessStatus {

	private final String requestTime;
	private final SuccessDetails success;

}
