package com.abg.stationMapping.ecom.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ServiceHeader {
	private final String brandCode;
	private final String channel;
}
