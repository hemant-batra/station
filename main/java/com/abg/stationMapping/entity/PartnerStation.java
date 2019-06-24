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
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Singular;

@Data
@Entity
@Table(name = "partner_station")
public class PartnerStation  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "partner_code")
	private String partnerCode;

	@Column(name = "station_code")
	private String stationCode;

	@Column(name = "station_name")
	private String stationName;

	@Column(name = "mad_enabled")
	private boolean madEnabled;
	
	@Column(name = "advance_booking_hour")
	private int advanceBookingHour;
	
	@Column(name = "key_pickup_location")
	private String keyPickupLocation;	
	
	@Column(name = "parking_place")
	private String parkingPlace;	
	
	@Column(name = "address_line_1")
	private String addressLine1;
	
	@Column(name = "address_line_2")
	private String addressLine2;
	
	@Column(name = "address_line_3")
	private String addressLine3;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state_name")
	private String stateName;
	
	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "lat")
	private String lat;

	@Column(name = "lat_long")
	private String latLong;

	@Column(name = "telephone")
	private String telephone;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "partner_station_id", referencedColumnName = "id", insertable = false, updatable = false)
	@Singular private Set<PartnerStationBusinessHour> partnerStationBusinessHours;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
			@JoinColumn(name = "partner_station_code", referencedColumnName = "station_code", insertable = false, updatable = false),
			@JoinColumn(name = "partner_code", referencedColumnName = "partner_code", insertable = false, updatable = false) })
	@Singular private Set<ABGStation> abgStations;

	
	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name="station_code",
	 * referencedColumnName="partner_station_code",insertable=false,updatable=false)
	 * private AvisStation avisStation;
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name="station_code",
	 * referencedColumnName="partner_station_code",insertable=false,
	 * updatable=false) private BudgetStation budgetStation;
	 */

	
}
