package com.abg.stationMapping.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.Singular;

@Data
@Entity
@Table(name = "abg_station")
public class ABGStation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "partner_code")
	private String partnerCode;

	@Column(name = "partner_station_code")
	private String partnerStationCode;

	@Column(name = "abg_station_code")
	private String abgStationCode;

	@Column(name = "brand")
	private String brand;
	
	@Column(name = "mad_enabled")
	private boolean madEnabled;

	@Column(name = "mad_category")
	private String madCategory;

	@Column(name = "advance_booking_hour")
	private int advanceBookingHour;
	
	@Column(name = "vehicle_class_group_id")
	private int vehicleClassGroupId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_class_group", referencedColumnName = "vehicle_class_group_id", insertable = false, updatable = false)
	@Singular private Set<VehicleClassGroup> vehicleClassGroups;
	

}
