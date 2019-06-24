package com.abg.stationMapping.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

import javax.validation.constraints.Size;

import org.apache.logging.log4j.Level;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abg.stationMapping.Exception.StationMappingException;
import com.abg.stationMapping.entity.ABGStation;
import com.abg.stationMapping.entity.PartnerStation;
import com.abg.stationMapping.logging.ELKLoggingUtil;
import com.abg.stationMapping.logging.LoggingUtil;
import com.abg.stationMapping.response.dto.PartnerStationMapping;
import com.abg.stationMapping.service.IStationMappingService;
import com.abg.stationMapping.util.Consts;
import com.abg.stationMapping.util.StationMappingBusinessProcess;
import com.abg.stationMapping.vo.StationMappingVO;

@Controller
@Validated
@RequestMapping("location/map/")
public class StationMappingController {

	private SimpleDateFormat dtf = new SimpleDateFormat(Consts.DATE_PATTERN);

	@Autowired
	private IStationMappingService stationMappingService;

	@Autowired
	private StationMappingBusinessProcess stationMappingBusinessProcess;

	@Autowired
	private ELKLoggingUtil elkLoggingUtil;

	@GetMapping("car_rental")
	public ResponseEntity<PartnerStationMapping> getStationMappingByPartnerStationCode(
			@RequestParam(name = "partner_code") String partnerCode,
			@RequestParam(name = "station_code") String partnerStationCode,
			@RequestParam(name = "locale", required = false) String locale,
			@RequestParam(name = "transaction_id", required = false) String transactionId,
			@Size(min = 32, max = 32) @RequestHeader(name = "client_id", required = true) String clientId)
			throws StationMappingException {

		PartnerStationMapping partnerStationMapping;
		StationMappingVO stationMappingVO = null;

		try {

			String requestTime = dtf.format(System.currentTimeMillis());
			String uuid = UUID.randomUUID().toString();
			HashMap<String, Object> hashMap = new HashMap<String, Object>();

			if (transactionId == null) {
				transactionId = uuid;
			}
			stationMappingVO = StationMappingVO.builder().requestTime(requestTime).uuid(uuid).partnerCode(partnerCode)
					.partnetStationCode(partnerStationCode).locale(locale).transactionId(transactionId).clientId(clientId)
					.build();

			elkLoggingUtil.sourceInitailize(hashMap, stationMappingVO);

			partnerStationMapping = stationMappingBusinessProcess.processStationMapping(hashMap, stationMappingVO);

			elkLoggingUtil.sourceEnd(hashMap, stationMappingVO);

		} catch (NullPointerException ex) {
			LoggingUtil.log("NullPointerException : getStationMappingByPartnerStationCode : " + ex, stationMappingVO,
					StationMappingController.class, Level.ERROR);
			throw new StationMappingException(ex);
		} catch (Exception ex) {
			LoggingUtil.log("Exception : getStationMappingByPartnerStationCode : " + ex, stationMappingVO,
					StationMappingController.class, Level.ERROR);
			throw new StationMappingException(ex);
		}
		return new ResponseEntity<PartnerStationMapping>(partnerStationMapping, HttpStatus.OK);
	}

	@GetMapping("partner")
	public ResponseEntity<PartnerStationMapping> getMeetAndGreet(
			@RequestParam(name = "partner_code") String partnerCode,
			@RequestParam(name = "brand") String brand,
			@RequestParam(name = "station_code") String abgStationCode,
			@RequestParam(name = "locale", required = false) String locale,
			@RequestParam(name = "transaction_id", required = false) String transactionId,
			@Size(min = 32, max = 32) @RequestHeader(name = "client_id", required = true) String clientId)
			throws StationMappingException {
		
		PartnerStationMapping partnerStationMapping = null;
		StationMappingVO stationMappingVO = null;
		ABGStation abgStation;
		try {

			String requestTime = dtf.format(System.currentTimeMillis());
			String uuid = UUID.randomUUID().toString();
			HashMap<String, Object> hashMap = new HashMap<String, Object>();

			if (transactionId == null) {
				transactionId = uuid;
			}
			
			stationMappingVO = StationMappingVO.builder().requestTime(requestTime).uuid(uuid).partnerCode(partnerCode)
					.abgStationCode(abgStationCode).brand(brand).locale(locale).transactionId(transactionId).clientId(clientId)
					.build();
			
			if(Consts.AVIS_BRAND.equalsIgnoreCase(brand)) {
				stationMappingVO = stationMappingVO.toBuilder().brandAbbr(Consts.AVIS_BRAND_ABBR).build();
			}else if(Consts.BUDGET_BRAND.equalsIgnoreCase(brand)) {
				stationMappingVO = stationMappingVO.toBuilder().brandAbbr(Consts.BUDGET_BRAND_ABBR).build();
			}
			
			elkLoggingUtil.sourceInitailize(hashMap, stationMappingVO);
			
			// Get partner code and partner station code based on input ABG station code
			abgStation = stationMappingService.getStationMappingByABGStationCode(stationMappingVO.getPartnerCode(),stationMappingVO.getAbgStationCode(),stationMappingVO.getBrandAbbr());
			stationMappingVO = stationMappingVO.toBuilder().partnetStationCode(abgStation.getPartnerStationCode()).build();

			partnerStationMapping = stationMappingBusinessProcess.processStationMapping(hashMap, stationMappingVO);

			elkLoggingUtil.sourceEnd(hashMap, stationMappingVO);

		} catch (NullPointerException ex) {
			LoggingUtil.log("NullPointerException : getStationMappingByPartnerStationCode : " + ex, stationMappingVO,
					StationMappingController.class, Level.ERROR);
			throw new StationMappingException(ex);
		} catch (Exception ex) {
			LoggingUtil.log("Exception : getStationMappingByPartnerStationCode : " + ex, stationMappingVO,
					StationMappingController.class, Level.ERROR);
			throw new StationMappingException(ex);
		}
		return new ResponseEntity<PartnerStationMapping>(partnerStationMapping, HttpStatus.OK);
	}
}