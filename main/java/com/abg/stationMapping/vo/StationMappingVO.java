package com.abg.stationMapping.vo;

import java.util.List;

import com.abg.stationMapping.response.dto.Day;
import com.abg.stationMapping.response.dto.HourSlot;
import com.abg.stationMapping.response.dto.Day.DayBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class StationMappingVO {

	private final String host;
	private final String requestTime;
	private final String partnerCode;
	private final String partnetStationCode;
	private final String abgStationCode;	
	private final String locale;
	private final String transactionId;
	private final String clientId;
	private final String uuid;
	private final String brand;
	private final String brandAbbr;
	private final String layer;
	private final String creationDate;
	private final String requestParam;
	private final String requestBody;
	private final String responseBody;
	private final String responseStatus;
	private final String responseErrorCode;
	private final String responseErrorDetails;
	
	
}
