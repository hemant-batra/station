package com.abg.stationMapping.service;

import java.util.List;

import com.abg.stationMapping.entity.ABGStation;
import com.abg.stationMapping.entity.PartnerStation;
import com.abg.stationMapping.vo.StationMappingVO;

public interface IStationMappingService {

	/*
	 * Station mapping based on partner station code
	 */
	PartnerStation getStationMappingByPartnerStationCode(String partnerCode, String partnerStationCode) throws Exception;

	ABGStation getStationMappingByABGStationCode(String partnerCode, String abgStationCode, String brand) throws Exception;
	
	List<PartnerStation> getAllStationMappingByPartnerStationCode();

	PartnerStation getMeetAndGreetAdjustTime(String partnerCode, String partnerStationMappingCode);

	
	
}
