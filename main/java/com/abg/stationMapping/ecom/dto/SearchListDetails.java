	package com.abg.stationMapping.ecom.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Builder
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SearchListDetails {
	private final String description;
	private final String locationCode;
	private final String latitude;
	private final String longitude;
	private final String stateCode;
	private final String stateName;
	private final String city;
	private final String country;
	private final String countyCode;
	private final String zipCode;
	private final String distance;
	private final String address1;
	private final String address2;
	private final String phoneNumber;
	private final String hoursOfOperation;
	private final String airportIndicator;
	private final String onLocation;
	private final String type;
	private final String selfServiceIndicator;
	private final String keyDropLoc;
	private final String regionNumber;
	@Singular private final List<Hours> hours;
	
}
