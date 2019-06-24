package com.abg.stationMapping.ecom.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@JsonIgnoreProperties
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class LocationResponse {
	private final String success;
	private final String errorCode;
	private final String errorMessage;
	private final ServiceHeader serviceHeader;
	@Singular
	private final List<SearchListDetails> searchListDetails;

}
