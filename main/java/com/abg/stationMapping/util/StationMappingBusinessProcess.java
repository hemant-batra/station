package com.abg.stationMapping.util;

import java.util.HashMap;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abg.stationMapping.Exception.StationMappingException;
import com.abg.stationMapping.ecom.dto.LocationResponse;
import com.abg.stationMapping.entity.ABGStation;
import com.abg.stationMapping.entity.PartnerStation;
import com.abg.stationMapping.logging.ELKLoggingUtil;
import com.abg.stationMapping.logging.LoggingUtil;
import com.abg.stationMapping.response.dto.PartnerStationMapping;
import com.abg.stationMapping.service.IStationMappingService;
import com.abg.stationMapping.vo.StationMappingVO;

import reactor.util.function.Tuple2;

@Component
public class StationMappingBusinessProcess {

	@Autowired
	private IStationMappingService stationMappingService;

	@Autowired
	private ThirdPartyInterface thirdPartyInterface;

	@Autowired
	private ELKLoggingUtil elkLoggingUtil;

	@Autowired
	private ResponseBuilder responseBuilder;

	public PartnerStationMapping processStationMapping(HashMap<String, Object> hashMap, StationMappingVO stationMappingVO)
			throws StationMappingException {

		PartnerStationMapping partnerStationMapping;
		PartnerStation partnerStation;
		LocationResponse locationResponse;
		ABGStation abgStation;

		try {
			LoggingUtil.log(
					"Start : processStationMapping : Brand : " + stationMappingVO.getBrand() + " Location Code : "
							+ stationMappingVO.getPartnetStationCode(),
					stationMappingVO, StationMappingBusinessProcess.class, Level.INFO);

			elkLoggingUtil.targetInitailize(hashMap, stationMappingVO);
			partnerStation = stationMappingService.getStationMappingByPartnerStationCode(stationMappingVO.getPartnerCode(),stationMappingVO.getPartnetStationCode());
			elkLoggingUtil.targetEnd(hashMap, stationMappingVO);

			partnerStationMapping = responseBuilder.preparePartnerStationMapping(partnerStation, stationMappingVO);

			if (partnerStation.getAbgStations().size() == 1) {
				abgStation = partnerStation.getAbgStations().iterator().next();
				locationResponse = thirdPartyInterface.ECOMLocationSearchInterface(abgStation.getBrand(),
						abgStation.getAbgStationCode(), stationMappingVO);

				partnerStationMapping = responseBuilder.prepareABGStationMapping(partnerStationMapping, abgStation,
						locationResponse, stationMappingVO);

			} else if (partnerStation.getAbgStations().size() == 2) {
				ABGStation abgStationAvis = partnerStation.getAbgStations().stream()
						.filter(obj -> Consts.AVIS_BRAND_ABBR.equals(obj.getBrand())).findFirst().get();
				ABGStation abgStationBudget = partnerStation.getAbgStations().stream()
						.filter(obj -> Consts.BUDGET_BRAND_ABBR.equals(obj.getBrand())).findFirst().get();

				Tuple2<LocationResponse, LocationResponse> locationResponseTuple = thirdPartyInterface
						.ECOMLocationSearchInterfaceParallel(abgStationAvis.getAbgStationCode(),
								abgStationBudget.getAbgStationCode(), stationMappingVO);

				partnerStationMapping = responseBuilder.prepareABGStationMapping(partnerStationMapping, abgStationAvis,
						locationResponseTuple.getT1(), stationMappingVO);

				partnerStationMapping = responseBuilder.prepareABGStationMapping(partnerStationMapping,
						abgStationBudget, locationResponseTuple.getT2(), stationMappingVO);
			}

			LoggingUtil.log(
					"End : processStationMapping : Brand : " + stationMappingVO.getBrand() + " Location Code : "
							+ stationMappingVO.getPartnetStationCode(),
					stationMappingVO, StationMappingBusinessProcess.class, Level.INFO);

			return partnerStationMapping;

		} catch (NullPointerException ex) {
			LoggingUtil.log("NullPointerException : processStationMapping : " + ex, stationMappingVO, ResponseBuilder.class,
					Level.ERROR);
			throw new StationMappingException(ex);
		} catch (Exception ex) {
			LoggingUtil.log("Exception : processStationMapping : " + ex, stationMappingVO, StationMappingBusinessProcess.class,
					Level.ERROR);
			throw new StationMappingException(ex);
		}

	}

}
