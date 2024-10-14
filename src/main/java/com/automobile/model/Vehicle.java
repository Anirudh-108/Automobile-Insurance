package com.automobile.model;

import com.automobile.enums.FuelType;
import com.automobile.enums.TransmissionType;
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
	private int yearOfPurchase;
	private double basePrice;
	private boolean previousClaim;

	private String registrationNo;

	@Enumerated(EnumType.STRING)
	private FuelType fuelType;

	@Enumerated(EnumType.STRING)
	private TransmissionType transmissionType;

	@Enumerated(EnumType.STRING)
	private VehicleCondition vehicleCondition;

	@Enumerated(EnumType.STRING)
	private ZoneType zoneType;	



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

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public TransmissionType getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(TransmissionType transmissionType) {
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


	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public int getYearOfPurchase() {
		return yearOfPurchase;
	}

	public void setYearOfPurchase(int yearOfPurchase) {
		this.yearOfPurchase = yearOfPurchase;
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
				+ ", modelName=" + modelName + ", variant=" + variant + ", yearOfPurchase=" + yearOfPurchase
				+ ", basePrice=" + basePrice + ", previousClaim=" + previousClaim + ", registrationNo=" + registrationNo
				+ ", fuelType=" + fuelType + ", transmissionType=" + transmissionType + ", vehicleCondition="
				+ vehicleCondition + ", zoneType=" + zoneType + ", customer=" + customer + "]";

	}

}
