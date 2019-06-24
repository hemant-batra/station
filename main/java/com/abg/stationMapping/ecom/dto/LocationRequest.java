package com.abg.stationMapping.ecom.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationRequest {
	
	private final String type;
	private final String brand;
	private final String city;
	private final ServiceHeader serviceHeader;
}
