package com.automobile.model;

import com.automobile.enums.VehicleCondition;
import com.automobile.enums.VehicleType;
import com.automobile.enums.ZoneType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

	private String manufacturerName;
	private String modelName;
	private String variant;
	private int manufacturingYear;
	private double basePrice;
	private String fuelType;
	private String transmissionType;

	@Enumerated(EnumType.STRING)
	private VehicleCondition vehicleCondition;

	@Enumerated(EnumType.STRING)
	private ZoneType zoneType;

	private boolean previousClaim;

	@ManyToOne
	private Customer customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public int getManufacturingYear() {
		return manufacturingYear;
	}

	public void setManufacturingYear(int manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public VehicleCondition getVehicleCondition() {
		return vehicleCondition;
	}

	public void setVehicleCondition(VehicleCondition vehicleCondition) {
		this.vehicleCondition = vehicleCondition;
	}

	public ZoneType getZoneType() {
		return zoneType;
	}

	public void setZoneType(ZoneType zoneType) {
		this.zoneType = zoneType;
	}

	public boolean isPreviousClaim() {
		return previousClaim;
	}

	public void setPreviousClaim(boolean previousClaim) {
		this.previousClaim = previousClaim;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vehicleType=" + vehicleType + ", manufacturerName=" + manufacturerName
				+ ", modelName=" + modelName + ", variant=" + variant + ", naufacturingYear=" + manufacturingYear
				+ ", basePrice=" + basePrice + ", fuelType=" + fuelType + ", transmissionType=" + transmissionType
				+ ", vehicleCondition=" + vehicleCondition + ", zoneType=" + zoneType + ", previousClaim="
				+ previousClaim + ", customer=" + customer + "]";
	}

}
