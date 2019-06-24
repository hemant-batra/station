package com.abg.stationMapping.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vehicle_class_group")
public class VehicleClassGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/*@Column(name = "vehicle_class_group")
	private int vehicleClassGroup;*/

	/*@Column(name = "vehicle_class_code_id")
	private int vehicleClassCodeId;*/

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "vehicle_class_code_id", referencedColumnName = "id", insertable = false, updatable = false)
	private VehicleClassCode vehicleClassCode;
}
