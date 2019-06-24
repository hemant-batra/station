package com.abg.stationMapping.response.dto;

import com.abg.stationMapping.response.dto.SuccessStatus.SuccessStatusBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Domain {
	private final String language;
	private final String channel;
}
