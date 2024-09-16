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
import com.automobile.enums.VehicleType;
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

		if (policyType.equals(PolicyType.Comprehensive.toString())) {
			basePremium = vehicle.getBasePrice() * 0.05; // comprehensive
		} else if (policyType.equals(PolicyType.ThirdParty.toString())) {
			basePremium = vehicle.getBasePrice() * 0.03; // third party
		}

		double premium = basePremium;

		double ageSurcharge = 0;
		if (vehicleAge >= 1 && vehicleAge <= 3) {
			ageSurcharge = basePremium * 0.05; // 1-3 years
		} else if (vehicleAge >= 4 && vehicleAge <= 6) {
			ageSurcharge = basePremium * 0.10; // 4-6 years
		} else if (vehicleAge > 6) {
			ageSurcharge = basePremium * 0.20; // more than 6 years
		}

		if (vehicle.getVehicleCondition().equals(VehicleCondition.Used)) {
			usedSurcharge = basePremium * 0.10; // vehicles
		}

		double zoneAdjustment = 0;
		switch (vehicle.getZoneType()) {
		case ZoneA:
			zoneAdjustment = basePremium * 0.15; // Zone A
			break;
		case ZoneB:
			zoneAdjustment = basePremium * 0.10; // Zone B
			break;
		case ZoneC:
			zoneAdjustment = basePremium * 0.05; // Zone C
			break;
		}

		double fuelTransmissionAdjustment = 0;
		if (vehicle.getVehicleType().toString().equals(VehicleType.Car.toString())) { // car
			if (vehicle.getTransmissionType().equals("Automatic")) { // automatic
				fuelTransmissionAdjustment += basePremium * 0.05;

				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.03; // automatic + petrol
				} else if (vehicle.getFuelType().equals("Diesel")) {
					fuelTransmissionAdjustment += basePremium * 0.05; // automatic + diesel
				}
			} else if (vehicle.getTransmissionType().equals("Manual")) {
				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.02; // petrol + manual
				} else if (vehicle.getFuelType().equals("Diesel")) {
					fuelTransmissionAdjustment += basePremium * 0.03; // diesel + manual
				}
			}
		} else if (vehicle.getVehicleType().toString().equals(VehicleType.Bike.toString())) {
			// Bike-specific fuel and transmission adjustments
			if (vehicle.getTransmissionType().equals("Geared")) {
				fuelTransmissionAdjustment += basePremium * 0.03; // geared bikes

				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.02; // petrol geared bikes
				}
			} else if (vehicle.getTransmissionType().equals("Non-Geared")) {
				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.01; // petrol non-geared bikes
				}
			}
		}

		if (vehicle.getFuelType().equals("EV")) {
			evDiscount = basePremium * 0.10; // EV
		}

		if (vehicle.isPreviousClaim()) {
			previousClaimsSurcharge = basePremium * 0.20; // previous claims
		}

		/*
		 * Calculating Coverage Amount
		 */
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
		;

		ShowPolicyDto dto = new ShowPolicyDto();

		if (vehicle.getVehicleType().toString().equals(VehicleType.Car.toString())
				&& policyType.equals(PolicyType.Comprehensive.toString()))
			dto.setDescription(
					"This Comprehensive Car Policy provides extensive coverage for your vehicle, protecting against third-party liabilities as well as damages from accidents, theft, fire, vandalism, and natural disasters. It offers peace of mind by covering repair or replacement costs for a wide range of incidents, ensuring all-around protection for your car.");
		else if (vehicle.getVehicleType().toString().equals(VehicleType.Bike.toString())
				&& policyType.equals(PolicyType.Comprehensive.toString()))
			dto.setDescription(
					"This Comprehensive Bike Policy offers full protection for your bike, covering third-party liabilities as well as damages caused by accidents, theft, fire, and natural disasters. It ensures financial security by covering repair or replacement costs, providing complete peace of mind for bike owners.");
		else if (vehicle.getVehicleType().toString().equals(VehicleType.Car.toString())
				&& policyType.equals(PolicyType.ThirdParty.toString()))
			dto.setDescription(
					"This Third-Party Car Policy covers damages or injuries you cause to others in an accident, including their property or vehicle. It is a mandatory and cost-effective policy that provides financial protection against third-party claims but does not cover damage to your own vehicle.");
		else if (vehicle.getVehicleType().toString().equals(VehicleType.Bike.toString())
				&& policyType.equals(PolicyType.ThirdParty.toString()))

			dto.setDescription(
					"This Third-Party Bike Policy provides coverage for damages or injuries you cause to others in an accident, including their property. It is a mandatory and affordable policy, offering financial protection against third-party claims but does not cover damages to your own bike.");

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
			basePremium = vehicle.getBasePrice() * 0.05; // comprehensive
		} else if (policyType.equals(PolicyType.ThirdParty.toString())) {
			basePremium = vehicle.getBasePrice() * 0.03; // third party
		}

		double premium = basePremium;

		double ageSurcharge = 0;
		if (vehicleAge >= 1 && vehicleAge <= 3) {
			ageSurcharge = basePremium * 0.05; // 1-3 years
		} else if (vehicleAge >= 4 && vehicleAge <= 6) {
			ageSurcharge = basePremium * 0.10; // 4-6 years
		} else if (vehicleAge > 6) {
			ageSurcharge = basePremium * 0.20; // more than 6 years
		}

		if (vehicle.getVehicleCondition().equals(VehicleCondition.Used)) {
			usedSurcharge = basePremium * 0.10; // vehicles
		}

		double zoneAdjustment = 0;
		switch (vehicle.getZoneType()) {
		case ZoneA:
			zoneAdjustment = basePremium * 0.15; // Zone A
			break;
		case ZoneB:
			zoneAdjustment = basePremium * 0.10; // Zone B
			break;
		case ZoneC:
			zoneAdjustment = basePremium * 0.05; // Zone C
			break;
		}

		double fuelTransmissionAdjustment = 0;
		if (vehicle.getVehicleType().toString().equals(VehicleType.Car.toString())) { // car
			if (vehicle.getTransmissionType().equals("Automatic")) { // automatic
				fuelTransmissionAdjustment += basePremium * 0.05;

				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.03; // automatic + petrol
				} else if (vehicle.getFuelType().equals("Diesel")) {
					fuelTransmissionAdjustment += basePremium * 0.05; // automatic + diesel
				}
			} else if (vehicle.getTransmissionType().equals("Manual")) {
				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.02; // petrol + manual
				} else if (vehicle.getFuelType().equals("Diesel")) {
					fuelTransmissionAdjustment += basePremium * 0.03; // diesel + manual
				}
			}
		} else if (vehicle.getVehicleType().toString().equals(VehicleType.Bike.toString())) {
			// Bike-specific fuel and transmission adjustments
			if (vehicle.getTransmissionType().equals("Geared")) {
				fuelTransmissionAdjustment += basePremium * 0.03; // geared bikes

				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.02; // petrol geared bikes
				}
			} else if (vehicle.getTransmissionType().equals("Non-Geared")) {
				if (vehicle.getFuelType().equals("Petrol")) {
					fuelTransmissionAdjustment += basePremium * 0.01; // petrol non-geared bikes
				}
			}
		}

		if (vehicle.getFuelType().equals("EV")) {
			evDiscount = basePremium * 0.10; // EV
		}

		if (vehicle.isPreviousClaim()) {
			previousClaimsSurcharge = basePremium * 0.20; // previous claims
		}

		/*
		 * Calculating Coverage Amount
		 */
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

		if (vehicle.getVehicleType().toString().equals(VehicleType.Car.toString())
				&& policyType.equals(PolicyType.Comprehensive.toString()))
			policy.setDescription(
					"This Comprehensive Car Policy provides extensive coverage for your vehicle, protecting against third-party liabilities as well as damages from accidents, theft, fire, vandalism, and natural disasters. It offers peace of mind by covering repair or replacement costs for a wide range of incidents, ensuring all-around protection for your car.");
		else if (vehicle.getVehicleType().toString().equals(VehicleType.Bike.toString())
				&& policyType.equals(PolicyType.Comprehensive.toString()))
			policy.setDescription(
					"This Comprehensive Bike Policy offers full protection for your bike, covering third-party liabilities as well as damages caused by accidents, theft, fire, and natural disasters. It ensures financial security by covering repair or replacement costs, providing complete peace of mind for bike owners.");
		else if (vehicle.getVehicleType().toString().equals(VehicleType.Car.toString())
				&& policyType.equals(PolicyType.ThirdParty.toString()))
			policy.setDescription(
					"This Third-Party Car Policy covers damages or injuries you cause to others in an accident, including their property or vehicle. It is a mandatory and cost-effective policy that provides financial protection against third-party claims but does not cover damage to your own vehicle.");
		else if (vehicle.getVehicleType().toString().equals(VehicleType.Bike.toString())
				&& policyType.equals(PolicyType.ThirdParty.toString()))
			policy.setDescription(
					"This Third-Party Bike Policy provides coverage for damages or injuries you cause to others in an accident, including their property. It is a mandatory and affordable policy, offering financial protection against third-party claims but does not cover damages to your own bike.");

		policy.setPolicyType(PolicyType.valueOf(policyType));
		policy.setPremiumAmount(premium);
		policy.setCoverageAmount(coverageAmount);
		policy.setTermLength(12);
		policy.setPolicyStatus(PolicyStatus.Active);

		return policy;
	}

	public CustomerPolicy buyPolicy(String customerUsername, Policy policy, Vehicle vehicle) {
		Customer customer = customerRepository.getCustomer(customerUsername);

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
