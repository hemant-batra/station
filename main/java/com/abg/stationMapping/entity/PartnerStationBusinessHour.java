package com.abg.stationMapping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "partner_station_business_hour")
public class PartnerStationBusinessHour implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "working_day")
	private String workingDay;

	@Column(name = "opening_hour")
	private String openingHour;

	@Column(name = "closing_hour")
	private String closingHour;

}
