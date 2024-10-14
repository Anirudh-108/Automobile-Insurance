package com.automobile.dto;

import java.time.LocalDate;

import com.automobile.enums.FuelType;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.enums.PolicyType;
import com.automobile.enums.TransmissionType;
import com.automobile.enums.VehicleCondition;
import com.automobile.enums.VehicleType;
import com.automobile.enums.ZoneType;

public class CustomerVehicleDto {
	
	private int policyId;
    private PolicyType policyType;
    private LocalDate buyingDate;

    private VehicleType vehicleType;
    private String manufacturerName;
    private String modelName;
    private int yearOfPurchase;	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public PolicyType getPolicyType() {
		return policyType;
	}
	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}
	public LocalDate getBuyingDate() {
		return buyingDate;
	}
	public void setBuyingDate(LocalDate buyingDate) {
		this.buyingDate = buyingDate;
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
	public int getYearOfPurchase() {
		return yearOfPurchase;
	}
	public void setYearOfPurchase(int yearOfPurchase) {
		this.yearOfPurchase = yearOfPurchase;
	}
	
	
	
	
	public CustomerVehicleDto(int policyId, PolicyType policyType, LocalDate buyingDate, 
            VehicleType vehicleType, String manufacturerName, 
            String modelName, int yearOfPurchase) {
				this.policyId = policyId;
				this.policyType = policyType;
				this.buyingDate = buyingDate;
				this.vehicleType = vehicleType;
				this.manufacturerName = manufacturerName;
				this.modelName = modelName;
				this.yearOfPurchase = yearOfPurchase;
}
	@Override
	public String toString() {
		return "CustomerVehicleDto [policyId=" + policyId + ", policyName=" + policyType + ", buyingDate=" + buyingDate
				+ ", vehicleType=" + vehicleType + ", manufacturerName=" + manufacturerName + ", modelName=" + modelName
				+ ", yearOfPurchase=" + yearOfPurchase + "]";
	}
    

	
}
