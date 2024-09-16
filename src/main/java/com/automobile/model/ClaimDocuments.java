package com.automobile.model;

import java.time.LocalDate;

import com.automobile.enums.VehicleClaimCondition;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClaimDocuments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private LocalDate dateOfIncident;
	private String driverLicenceNo;
	private String firCopy;
	private String videoProofOfDamage;
	private double damageCostEstimate;

	@Enumerated(EnumType.STRING)
	private VehicleClaimCondition vehicleClaimCondition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateOfIncident() {
		return dateOfIncident;
	}

	public void setDateOfIncident(LocalDate dateOfIncident) {
		this.dateOfIncident = dateOfIncident;
	}

	public String getDriverLicenceNo() {
		return driverLicenceNo;
	}

	public void setDriverLicenceNo(String driverLicenceNo) {
		this.driverLicenceNo = driverLicenceNo;
	}

	public String getFirCopy() {
		return firCopy;
	}

	public void setFirCopy(String firCopy) {
		this.firCopy = firCopy;
	}

	public String getVideoProofOfDamage() {
		return videoProofOfDamage;
	}

	public void setVideoProofOfDamage(String videoProofOfDamage) {
		this.videoProofOfDamage = videoProofOfDamage;
	}

	public VehicleClaimCondition getVehicleClaimCondition() {
		return vehicleClaimCondition;
	}

	public void setVehicleClaimCondition(VehicleClaimCondition vehicleClaimCondition) {
		this.vehicleClaimCondition = vehicleClaimCondition;
	}

	public double getDamageCostEstimate() {
		return damageCostEstimate;
	}

	public void setDamageCostEstimate(double damageCostEstimate) {
		this.damageCostEstimate = damageCostEstimate;
	}

	@Override
	public String toString() {
		return "ClaimDocuments [id=" + id + ", dateOfIncident=" + dateOfIncident + ", driverLicenceNo="
				+ driverLicenceNo + ", firCopy=" + firCopy + ", videoProofOfDamage=" + videoProofOfDamage
				+ ", damageCostEstimate=" + damageCostEstimate + ", vehicleClaimCondition=" + vehicleClaimCondition
				+ "]";
	}

}
