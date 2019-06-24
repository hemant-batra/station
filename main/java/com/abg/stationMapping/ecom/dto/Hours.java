package com.abg.stationMapping.ecom.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Hours {
	private final String dayOfWeek;
	private final String firstOpen;
	private final String firstClose;
	private final String secondOpen;
	private final String secondClose;
	private final String earlyOpen;
	private final String lateClose;
	private final String seasonOpen;
	private final String seasonClose;
}
