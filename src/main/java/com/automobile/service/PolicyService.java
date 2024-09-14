package com.automobile.service;

import java.time.LocalDate;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.dto.ShowPolicyDto;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.enums.PolicyStatus;
import com.automobile.enums.PolicyType;
import com.automobile.enums.VehicleCondition;
import com.automobile.model.Customer;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Policy;
import com.automobile.model.Vehicle;
import com.automobile.repository.CustomerPolicyRepository;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.PolicyRepository;
import com.automobile.repository.VehicleRepository;

@Service
public class PolicyService {

	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;

	public Policy addPolicy(Policy policy) {
		return policyRepository.save(policy);
	}

	public ShowPolicyDto showPolicy(Vehicle vehicle, String policyType) {
		int currentYear = Year.now().getValue();
		int vehicleAge = currentYear - vehicle.getManufacturingYear();
		double basePremium = 0;
		double evDiscount = 0;
		double usedSurcharge = 0;
		double previousClaimsSurcharge = 0;

		ShowPolicyDto dto = new ShowPolicyDto();

		if (policyType.equals(PolicyType.Comprehensive.toString())) {
			basePremium = vehicle.getBasePrice() * 0.05; // 5% for comprehensive
		} else if (policyType.equals(PolicyType.ThirdParty.toString())) {
			basePremium = vehicle.getBasePrice() * 0.03; // 3% for third party
		}

		double premium = basePremium;

		double ageSurcharge = 0;
		if (vehicleAge >= 1 && vehicleAge <= 3) {
			ageSurcharge = basePremium * 0.05; // Add 5% for 1-3 years
		} else if (vehicleAge >= 4 && vehicleAge <= 6) {
			ageSurcharge = basePremium * 0.10; // Add 10% for 4-6 years
		} else if (vehicleAge > 6) {
			ageSurcharge = basePremium * 0.20; // Add 20% for vehicles older than 6 years
		}

		if (vehicle.getVehicleCondition().equals(VehicleCondition.Used)) {
			usedSurcharge = basePremium * 0.10; // 10% surcharge for used vehicles
		}

		double zoneAdjustment = 0;
		switch (vehicle.getZoneType()) {
		case ZoneA:
			zoneAdjustment = basePremium * 0.15; // 15% for Zone A
			break;
		case ZoneB:
			zoneAdjustment = basePremium * 0.10; // 10% for Zone B
			break;
		case ZoneC:
			zoneAdjustment = basePremium * 0.05; // 5% for Zone C
			break;
		}

		double fuelTransmissionAdjustment = 0;
		if (vehicle.getTransmissionType().equals("Automatic")) {
			fuelTransmissionAdjustment += basePremium * 0.05; // 5% for automatic transmission
			if (vehicle.getFuelType().equals("Petrol")) {
				fuelTransmissionAdjustment += basePremium * 0.03; // 3% for petrol automatic
			} else if (vehicle.getFuelType().equals("Diesel")) {
				fuelTransmissionAdjustment += basePremium * 0.05; // 5% for diesel automatic
			}
		} else if (vehicle.getTransmissionType().equals("Manual")) {
			if (vehicle.getFuelType().equals("Petrol")) {
				fuelTransmissionAdjustment += basePremium * 0.02; // 2% for petrol manual
			} else if (vehicle.getFuelType().equals("Diesel")) {
				fuelTransmissionAdjustment += basePremium * 0.03; // 3% for diesel manual
			}
		}

		if (vehicle.getFuelType().equals("EV")) {
			evDiscount = basePremium * 0.10; // 10% discount for EV
		}

		if (vehicle.isPreviousClaim()) {
			previousClaimsSurcharge = basePremium * 0.20; // 20% surcharge for previous claims
		}

		// Calculating Coverage Amount

		double coverageAmount;
		if (policyType.equals(PolicyType.Comprehensive.toString())) {
			coverageAmount = vehicle.getBasePrice() * 0.80;
		} else if (policyType.equals(PolicyType.ThirdParty.toString())) {
			coverageAmount = vehicle.getBasePrice() * 0.50;
		} else {
			throw new IllegalArgumentException("Invalid policy type");
		}

		premium = basePremium + ageSurcharge + usedSurcharge + zoneAdjustment + fuelTransmissionAdjustment
				+ previousClaimsSurcharge - evDiscount;

		dto.setPolicyType(PolicyType.valueOf(policyType).toString());
		dto.setPremiumAmount(premium);
		dto.setCoverageAmount(coverageAmount);
		dto.setTermLength("12 Months");

		return dto;

	}

	public Policy getPolicy(Vehicle vehicle, String policyType) {
		int currentYear = Year.now().getValue();
		int vehicleAge = currentYear - vehicle.getManufacturingYear();
		double basePremium = 0;
		double evDiscount = 0;
		double usedSurcharge = 0;
		double previousClaimsSurcharge = 0;

		if (policyType.equals(PolicyType.Comprehensive.toString())) {
			basePremium = vehicle.getBasePrice() * 0.05; // 5% for comprehensive
		} else if (policyType.equals(PolicyType.ThirdParty.toString())) {
			basePremium = vehicle.getBasePrice() * 0.03; // 3% for third party
		}

		double premium = basePremium;

		double ageSurcharge = 0;
		if (vehicleAge >= 1 && vehicleAge <= 3) {
			ageSurcharge = basePremium * 0.05; // Add 5% for 1-3 years
		} else if (vehicleAge >= 4 && vehicleAge <= 6) {
			ageSurcharge = basePremium * 0.10; // Add 10% for 4-6 years
		} else if (vehicleAge > 6) {
			ageSurcharge = basePremium * 0.20; // Add 20% for vehicles older than 6 years
		}

		if (vehicle.getVehicleCondition().equals(VehicleCondition.Used)) {
			usedSurcharge = basePremium * 0.10; // 10% surcharge for used vehicles
		}

		double zoneAdjustment = 0;
		switch (vehicle.getZoneType()) {
		case ZoneA:
			zoneAdjustment = basePremium * 0.15; // 15% for Zone A
			break;
		case ZoneB:
			zoneAdjustment = basePremium * 0.10; // 10% for Zone B
			break;
		case ZoneC:
			zoneAdjustment = basePremium * 0.05; // 5% for Zone C
			break;
		}

		double fuelTransmissionAdjustment = 0;
		if (vehicle.getTransmissionType().equals("Automatic")) {
			fuelTransmissionAdjustment += basePremium * 0.05; // 5% for automatic transmission
			if (vehicle.getFuelType().equals("Petrol")) {
				fuelTransmissionAdjustment += basePremium * 0.03; // 3% for petrol automatic
			} else if (vehicle.getFuelType().equals("Diesel")) {
				fuelTransmissionAdjustment += basePremium * 0.05; // 5% for diesel automatic
			}
		} else if (vehicle.getTransmissionType().equals("Manual")) {
			if (vehicle.getFuelType().equals("Petrol")) {
				fuelTransmissionAdjustment += basePremium * 0.02; // 2% for petrol manual
			} else if (vehicle.getFuelType().equals("Diesel")) {
				fuelTransmissionAdjustment += basePremium * 0.03; // 3% for diesel manual
			}
		}

		if (vehicle.getFuelType().equals("EV")) {
			evDiscount = basePremium * 0.10; // 10% discount for EV
		}

		if (vehicle.isPreviousClaim()) {
			previousClaimsSurcharge = basePremium * 0.20; // 20% surcharge for previous claims
		}

		// Calculating Coverage Amount

		double coverageAmount;
		if (policyType.equals(PolicyType.Comprehensive.toString())) {
			coverageAmount = vehicle.getBasePrice() * 0.80;
		} else if (policyType.equals(PolicyType.ThirdParty.toString())) {
			coverageAmount = vehicle.getBasePrice() * 0.50;
		} else {
			throw new IllegalArgumentException("Invalid policy type");
		}

		premium = basePremium + ageSurcharge + usedSurcharge + zoneAdjustment + fuelTransmissionAdjustment
				+ previousClaimsSurcharge - evDiscount;

		Policy policy = new Policy();
		policy.setPolicyType(PolicyType.valueOf(policyType));
		policy.setPremiumAmount(premium);
		policy.setCoverageAmount(coverageAmount);
		policy.setTermLength(12);

		return policy;
	}

	public CustomerPolicy buyPolicy(String empUsername, Policy policy, Vehicle vehicle) {
		Customer customer = customerRepository.getEmployee(empUsername);

		vehicle.setCustomer(customer);
		vehicleRepository.save(vehicle);

		policy = addPolicy(policy);
		policy.setPolicyStatus(PolicyStatus.Active);
		policyRepository.save(policy);

		CustomerPolicy customerPolicy = new CustomerPolicy();
		customerPolicy.setCustomer(customer);
		customerPolicy.setPolicy(policy);
		customerPolicy.setBuyingDate(LocalDate.now());
		customerPolicy.setPolicyRequestStatus(PolicyRequestStatus.Requested);

		return customerPolicyRepository.save(customerPolicy);
	}

}
