package com.abg.stationMapping.response.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerStationMapping {

	private final SuccessStatus status;
	private final Transaction transaction;
	private final Domain domain;
	private final Partner partner;
	private final Location partner_location;
	@Singular private final List<Location> car_rental_locations;

}
