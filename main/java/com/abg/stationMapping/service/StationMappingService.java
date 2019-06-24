package com.abg.stationMapping.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abg.stationMapping.entity.ABGStation;
import com.abg.stationMapping.entity.PartnerStation;
import com.abg.stationMapping.logging.LoggingUtil;
import com.abg.stationMapping.repository.StationMappingRepository;
import com.abg.stationMapping.util.ResponseBuilder;
import com.abg.stationMapping.vo.StationMappingVO;

@Service
public class StationMappingService implements IStationMappingService {

	@Autowired
	private StationMappingRepository stationMappingRepository;

	@Override
	// @Cacheable(value="stationMappingCacheTEMP", key= "#partnerCode + '_' +
	// #partnerStationCode", unless="#result == null")
	public PartnerStation getStationMappingByPartnerStationCode(String partnerCode, String partnerStationCode) throws Exception {
		
		LoggingUtil.log("Start : getStationMappingByPartnerStationCode : partnerCode : " + partnerCode
				+ " partnerStationCode : " + partnerStationCode, null, StationMappingService.class, Level.INFO);

		return stationMappingRepository.findPartnerStationByStationCode(partnerCode, partnerStationCode);
	}

	@Override
	// @Cacheable(value="stationMappingCacheTEMP", key= "#partnerCode + '_' +
	// #abgStationCode + '_' + #brand", unless="#result == null")
	public ABGStation getStationMappingByABGStationCode(String partnerCode, String abgStationCode, String brand) throws Exception {
		
		LoggingUtil.log("Start : getStationMappingByABGStationCode : partnerCode : " + partnerCode
				+ " abgStationCode : " + abgStationCode + " brand : " + brand, 
				null, StationMappingService.class, Level.INFO);

		return stationMappingRepository.findABGStationByStationCode(partnerCode, abgStationCode, brand);
	}
	
	@Override
	// @Cacheable(value="stationMappingCacheTEMP", key= "#partnerCode + '_' +
	// #partnerStationMappingCode", unless="#result == null")
	public PartnerStation getMeetAndGreetAdjustTime(String partnerCode, String partnerStationMappingCode) {

		return stationMappingRepository.findPartnerStationByStationCode(partnerCode, partnerStationMappingCode);
	}

	@Override
	// @Cacheable(value= "allStationMappingCacheTEMP", unless= "#result.size() ==
	// 0")
	public List<PartnerStation> getAllStationMappingByPartnerStationCode() {
		List<PartnerStation> partnerStationList = new ArrayList<>();
		stationMappingRepository.findAllPartnerStationByStationCode().forEach(e -> partnerStationList.add(e));
		return partnerStationList;
	}

	/*
	 * Sample Code for CRUD Operation
	 */

	/*
	 * @Override
	 * 
	 * @Cacheable(value="sncfStationMappingCache", key= "#p0") public
	 * SNCFStationMapping getSNCFStationMappingByCode(String sncfStationMappingCode)
	 * { System.out.println("--- Inside getSNCFStationMappingByCode() ---");
	 * //System.out.println("--- Inside getSNCFStationMappingByCode() --- : " +
	 * sncfStationMappingRepository.findById(sncfStationMappingCode).get().getStatus
	 * ()); //return
	 * sncfStationMappingRepository.findById(sncfStationMappingCode).get(); return
	 * sncfStationMappingRepository.findByStatus("InValid");
	 * 
	 * }
	 * 
	 * @Override
	 * 
	 * @Cacheable(value= "allSNCFStationMappingCache", unless=
	 * "#result.size() == 0") public List<SNCFStationMapping>
	 * getAllSNCFStationMappings(){
	 * System.out.println("--- Inside getAllSNCFStationMappings() ---");
	 * List<SNCFStationMapping> list = new ArrayList<>();
	 * sncfStationMappingRepository.findAll().forEach(e -> list.add(e)); return
	 * list; }
	 * 
	 * @Override
	 * 
	 * @Caching( put= { @CachePut(value= "sncfStationMappingCache", key=
	 * "#sncfStationMapping.sncfStationCode") }, evict= { @CacheEvict(value=
	 * "allSNCFStationMappingCache", allEntries= true) } ) public SNCFStationMapping
	 * addSNCFStationMapping(SNCFStationMapping sncfStationMapping){
	 * System.out.println("--- Inside addSNCFStationMapping() ---"); return
	 * sncfStationMappingRepository.save(sncfStationMapping); }
	 * 
	 * @Override
	 * 
	 * @Caching( put= { @CachePut(value= "sncfStationMappingCache", key=
	 * "#sncfStationMapping.sncfStationCode") }, evict= { @CacheEvict(value=
	 * "allSNCFStationMappingCache", allEntries= true) } ) public SNCFStationMapping
	 * updateSNCFStationMapping(SNCFStationMapping sncfStationMapping) {
	 * System.out.println("--- Inside updateSNCFStationMapping() ---"); return
	 * sncfStationMappingRepository.save(sncfStationMapping); }
	 * 
	 * @Override
	 * 
	 * @Caching( evict= {
	 * 
	 * @CacheEvict(value= "sncfStationMappingCache", key= "#p0"),
	 * 
	 * @CacheEvict(value= "allSNCFStationMappingCache", allEntries= true) } ) public
	 * void deleteSNCFStationMapping(String sncfStationMappingCode) {
	 * System.out.println("--- Inside deleteSNCFStationMapping() ---");
	 * sncfStationMappingRepository.delete(sncfStationMappingRepository.findById(
	 * sncfStationMappingCode).get()); }
	 */

}