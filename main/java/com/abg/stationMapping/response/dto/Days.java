package com.abg.stationMapping.response.dto;

import com.abg.stationMapping.response.dto.SuccessStatus.SuccessStatusBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Days {
	private final Day monday;
	private final Day tuesday;
	private final Day wednesday;
	private final Day thursday;
	private final Day friday;
	private final Day saturday;
	private final Day sunday;
	private final Day holiday;
	
}
