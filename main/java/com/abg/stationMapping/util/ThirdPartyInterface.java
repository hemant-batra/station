package com.abg.stationMapping.util;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.abg.stationMapping.Exception.StationMappingException;
import com.abg.stationMapping.config.AppProperties;
import com.abg.stationMapping.ecom.dto.LocationRequest;
import com.abg.stationMapping.ecom.dto.LocationResponse;
import com.abg.stationMapping.ecom.dto.ServiceHeader;
import com.abg.stationMapping.logging.LoggingUtil;
import com.abg.stationMapping.vo.StationMappingVO;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
public class ThirdPartyInterface {

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private ExternalWebClient externalWebClient;

	public LocationResponse ECOMLocationSearchInterface(String brand, String locationCode, StationMappingVO stationMappingVO)
			throws StationMappingException {

		LoggingUtil.log("Start : ECOMLocationSearchInterface : Brand : " + brand + " Location Code : " + locationCode,
				stationMappingVO, ThirdPartyInterface.class, Level.INFO);

		String ecomEndpoint = "";
		if (Consts.AVIS_BRAND_ABBR.equals(brand)) {
			ecomEndpoint = appProperties.getEcom().getAvisURL();
		} else if (Consts.BUDGET_BRAND_ABBR.equals(brand)) {
			ecomEndpoint = appProperties.getEcom().getBudgetURL();
		}
		// Prepare Location ECOM Request
		ServiceHeader serviceHeader = ServiceHeader.builder().brandCode(brand)
				.channel(appProperties.getEcom().getChannel()).build();
		LocationRequest locationRequest = LocationRequest.builder().brand(brand)
				.type(appProperties.getEcom().getLocationProximity()).city(locationCode).serviceHeader(serviceHeader)
				.build();

		WebClient webClient = externalWebClient.getWebClient(stationMappingVO);

		LocationResponse locationResponse = webClient.post().uri(ecomEndpoint)
				.header(Consts.HEADER_CONTENT_TYPE_KEY, Consts.HEADER_CONTENT_TYPE_APP_JOSN_VALUE)
				.header(Consts.HEADER_AUTHORIZATION_KEY, appProperties.getEcom().getAuthorization())
				.syncBody(locationRequest).retrieve().onStatus(HttpStatus::is4xxClientError, response -> {
					return Mono.error(new Exception());
				}).onStatus(HttpStatus::is5xxServerError, response -> {
					return Mono.error(new Exception());
				}).bodyToMono(LocationResponse.class).block();

		LoggingUtil.log("End : ECOMLocationSearchInterface : Brand : " + brand + " Location Code : " + locationCode,
				stationMappingVO, ThirdPartyInterface.class, Level.ERROR);

		return locationResponse;
	}

	public Tuple2<LocationResponse, LocationResponse> ECOMLocationSearchInterfaceParallel(String locationCodeAvis,
			String locationCodeBudget, StationMappingVO stationMappingVO) throws StationMappingException {

		LoggingUtil.log(
				"Start : ECOMLocationSearchInterfaceParallel : Avis Location Code : " + locationCodeAvis
						+ " Budget Location Code : " + locationCodeBudget,
				stationMappingVO, ThirdPartyInterface.class, Level.INFO);

		// Prepare Location Avis ECOM Request
		ServiceHeader serviceHeaderAvis = ServiceHeader.builder().brandCode(Consts.AVIS_BRAND_ABBR)
				.channel(appProperties.getEcom().getChannel()).build();
		LocationRequest locationRequestAvis = LocationRequest.builder().brand(Consts.AVIS_BRAND_ABBR)
				.type(appProperties.getEcom().getLocationProximity()).city(locationCodeAvis)
				.serviceHeader(serviceHeaderAvis).build();

		WebClient webClientAvis = externalWebClient.getWebClient(stationMappingVO);

		Mono<LocationResponse> locationResponseAvis = webClientAvis.post().uri(appProperties.getEcom().getAvisURL())
				.header(Consts.HEADER_CONTENT_TYPE_KEY, Consts.HEADER_CONTENT_TYPE_APP_JOSN_VALUE)
				.header(Consts.HEADER_AUTHORIZATION_KEY, appProperties.getEcom().getAuthorization())
				.syncBody(locationRequestAvis).retrieve().onStatus(HttpStatus::is4xxClientError, response -> {
					return Mono.error(new Exception());
				}).onStatus(HttpStatus::is5xxServerError, response -> {
					return Mono.error(new Exception());
				}).bodyToMono(LocationResponse.class);

		// Prepare Location Budget ECOM Request
		ServiceHeader serviceHeaderBudget = ServiceHeader.builder().brandCode(Consts.BUDGET_BRAND_ABBR)
				.channel(appProperties.getEcom().getChannel()).build();
		LocationRequest locationRequestBudget = LocationRequest.builder().brand(Consts.BUDGET_BRAND_ABBR)
				.type(appProperties.getEcom().getLocationProximity()).city(locationCodeBudget)
				.serviceHeader(serviceHeaderBudget).build();

		WebClient webClientBudget = externalWebClient.getWebClient(stationMappingVO);

		Mono<LocationResponse> locationResponseBudget = webClientBudget.post()
				.uri(appProperties.getEcom().getBudgetURL())
				.header(Consts.HEADER_CONTENT_TYPE_KEY, Consts.HEADER_CONTENT_TYPE_APP_JOSN_VALUE)
				.header(Consts.HEADER_AUTHORIZATION_KEY, appProperties.getEcom().getAuthorization())
				.syncBody(locationRequestBudget).retrieve().onStatus(HttpStatus::is4xxClientError, response -> {
					return Mono.error(new Exception());
				}).onStatus(HttpStatus::is5xxServerError, response -> {
					return Mono.error(new Exception());
				}).bodyToMono(LocationResponse.class);

		Tuple2<LocationResponse, LocationResponse> locationResponseTuple = locationResponseAvis
				.zipWith(locationResponseBudget).block();

		LoggingUtil.log(
				"End : ECOMLocationSearchInterfaceParallel : Avis Location Code : " + locationCodeAvis
						+ " Budget Location Code : " + locationCodeBudget,
				stationMappingVO, ThirdPartyInterface.class, Level.INFO);

		System.out.println(locationResponseTuple);
		return locationResponseTuple;

	}
}
