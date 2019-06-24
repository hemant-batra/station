package com.abg.stationMapping.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
public class HourSlot {
	private final String open;
	private final String close;
}
