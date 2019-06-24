package com.abg.stationMapping.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
	private final String address_line_1;
	private final String address_line_2;
	private final String address_line_3;
	private final String city;
	private final String state_name;
	private final String postal_code;
	private final String country_code;
	private final String lat;
	@JsonProperty("long")
	private final String lat_long;
}
