package com.abg.stationMapping.response.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeetGreet {
	private final boolean enabled;
	private final int reservation_lead_time;
	private final List<String> vehicle_class_codes;
	private final String parking_remarks;
	private final String key_pickup_remarks;
}
