package com.abg.stationMapping.response.dto;

import com.abg.stationMapping.response.dto.SuccessStatus.SuccessStatusBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
	private final String brand;
	private final String code;
	private final String name;
	private final String hours;
	private final String airport_location;
	private final String key_drop;
	private final String type;
	private final Days days;
	private final MeetGreet meet_greet;
	private final Address address;
	private final String telephone;
}
