package com.abg.stationMapping.response.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Singular;

@Data
@Setter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Day {
	@Singular private final List<HourSlot> hourSlots;
	
	
}
