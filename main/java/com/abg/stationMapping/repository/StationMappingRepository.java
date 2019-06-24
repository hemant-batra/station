package com.abg.stationMapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.abg.stationMapping.entity.ABGStation;
import com.abg.stationMapping.entity.PartnerStation;

public interface StationMappingRepository extends CrudRepository<PartnerStation, String> {

	@Query("from PartnerStation")
	List<PartnerStation> findAllPartnerStationByStationCode();

	@Query("from PartnerStation where stationCode = :partnerStationCode and partnerCode = :partnerCode")
	PartnerStation findPartnerStationByStationCode(@Param("partnerCode") String partnerCode,
			@Param("partnerStationCode") String partnerStationCode);
	
	@Query("from ABGStation where abgStationCode = :abgStationCode and partnerCode = :partnerCode and brand = :brand")
	ABGStation findABGStationByStationCode(@Param("partnerCode") String partnerCode,
			@Param("abgStationCode") String abgStationCode, @Param("brand") String brand);

}
