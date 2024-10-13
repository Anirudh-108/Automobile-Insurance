package com.automobile.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automobile.dto.ClaimPolicyDto;
import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.enums.PolicyStatus;
import com.automobile.enums.VehicleClaimCondition;
import com.automobile.enums.VehicleType;
import com.automobile.exception.CannotClaimPolicyException;
import com.automobile.model.ClaimDetails;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Customer;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Policy;
import com.automobile.model.Vehicle;
import com.automobile.repository.ClaimDetailsRepository;
import com.automobile.repository.ClaimPolicyRepository;
import com.automobile.repository.CustomerPolicyRepository;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.PolicyRepository;
import com.automobile.repository.VehicleRepository;

@Service
public class ClaimPolicyService {

	@Autowired
	public ClaimPolicyRepository claimPolicyRepository;

	@Autowired
	public VehicleRepository vehicleRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;

	@Autowired
	private ClaimDetailsRepository claimDetailsRepository;

	@Autowired
	private PolicyRepository policyRepository;

	private Logger logger = LoggerFactory.getLogger(ClaimPolicyService.class);

//	@Autowired
//	private PolicyRepository policyRepository;

	public List<Policy> getAllActivePolicy(String customerUsername) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
		int customerId = customerDB.getId();

		logger.info("Getting all active policies from DB");
		return claimPolicyRepository.findPolicyByStatus(customerId, PolicyStatus.Active);
	}

	public ClaimPolicy claimPolicy(int policyId, String customerUsername, ClaimDetails claimDetails)
			throws CannotClaimPolicyException {

		CustomerPolicy customerPolicy = customerPolicyRepository.getCustomerPolicyByPolicyId(policyId, customerUsername)
				.get();

		if (customerPolicy.getPolicyRequestStatus().toString()
				.equalsIgnoreCase(PolicyRequestStatus.Requested.toString())) {
			logger.error("Cannot claim this policy as this policy is not approved yet, Exception thrown...");
			throw new CannotClaimPolicyException("Cannot claim this policy as this policy is not approved yet.");
		}
		if (customerPolicy.getPolicyRequestStatus().toString()
				.equalsIgnoreCase(PolicyRequestStatus.Cancelled.toString())) {
			logger.error("Cannot claim this policy as this policy is cancelled, Exception thrown...");
			throw new CannotClaimPolicyException("Cannot claim this policy as this policy is cancelled.");
		}

		// for changing policy status after claim
//		Policy policy = policyRepository.findById(policyId).get();
//		policy.setPolicyStatus(PolicyStatus.Claimed);
//		policyRepository.save(policy);

		Customer customer = customerRepository.getCustomer(customerUsername);
//		int customerId = customer.getId();

		Policy policy = policyRepository.findById(policyId).get();

		double claimAmount = calculateClaimAmount(policy, claimDetails);

		claimDetails = claimDetailsRepository.save(claimDetails);

		ClaimPolicy claimPolicy = new ClaimPolicy();

		claimPolicy.setClaimDocuments(claimDetails);

		claimPolicy.setClaimAmount(claimAmount);
		claimPolicy.setClaimDate(LocalDate.now());
		claimPolicy.setClaimStatus(ClaimStatus.Pending);
		claimPolicy.setCustomer(customer);
		claimPolicy.setPolicy(policy);

		logger.info("Adding policy claim details to DB " + claimPolicy);
		return claimPolicyRepository.save(claimPolicy);
	}

	// Depreciation calculation based on vehicle age
	private double getCarDepreciation(int vehicleAge) {
		if (vehicleAge < 1) { // 6 months to 1 year
			return 0.05;
		} else if (vehicleAge < 2) { // 1 to 2 years
			return 0.10;
		} else if (vehicleAge < 3) { // 2 to 3 years
			return 0.15;
		} else if (vehicleAge < 4) { // 3 to 4 years
			return 0.20;
		} else if (vehicleAge < 5) { // 4 to 5 years
			return 0.30;
		} else { // More than 5 years
			return 0.50;
		}
	}

	private double getBikeDepreciation(int BikeAge) {
		if (BikeAge < 1) { // < 1 year
			return 0.15;
		} else if (BikeAge < 2) { // 1 to 2 years
			return 0.20;
		} else if (BikeAge < 3) { // 2 to 3 years
			return 0.30;
		} else if (BikeAge < 4) { // 3 to 4 years
			return 0.40;
		} else if (BikeAge < 5) { // 4 to 5 years
			return 0.50;
		} else { // More than 5 years
			return 0.60;
		}
	}

	// Final claim amount calculation
	public double calculateClaimAmount(Policy policy, ClaimDetails claimDetails) {

		Vehicle vehicle = policy.getVehicle();

		int currentYear = LocalDate.now().getYear();
		int vehicleAge = currentYear - vehicle.getYearOfPurchase();
		int bikeAge = currentYear - vehicle.getYearOfPurchase();
		double claimAmount = 0;
		double dentsSub = 0;

		double Cardeductible = 1500;
		double Bikedeductible = 100;
		double Cardepreciation = getCarDepreciation(vehicleAge);
		double Bikedepreciation = getBikeDepreciation(bikeAge);

		if (claimDetails.getVehicleClaimCondition() == VehicleClaimCondition.No_dents) {
			dentsSub = 0;
		} else if (claimDetails.getVehicleClaimCondition() == VehicleClaimCondition.Few_dents_with_few_scratches) {
			dentsSub = 1000;
		} else if (claimDetails
				.getVehicleClaimCondition() == VehicleClaimCondition.Medium_dents_with_medium_scratches) {
			dentsSub = 1500;
		} else if (claimDetails
				.getVehicleClaimCondition() == VehicleClaimCondition.Medium_dents_with_medium_scratches) {
			dentsSub = 2000;
		} else if (claimDetails.getVehicleClaimCondition() == VehicleClaimCondition.More_dents_with_scratches) {
			dentsSub = 2500;
		}

		if (vehicle.getVehicleType() == VehicleType.Car) {
			claimAmount = (claimDetails.getDamageCostEstimate() - Cardeductible - dentsSub) * (1 - Cardepreciation);
		} else if (vehicle.getVehicleType() == VehicleType.Bike) {
			claimAmount = (claimDetails.getDamageCostEstimate() - Bikedeductible) * (1 - Bikedepreciation);
		}
		return claimAmount;
	}

	public Page<ClaimPolicyDto> getAllClaims(String customerUsername, Pageable pageable) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		Page<ClaimPolicy> claimPolicyList = claimPolicyRepository.getAllClaims(customerId, pageable);

		List<ClaimPolicyDto> dtoList = new ArrayList<>();

		for (ClaimPolicy c : claimPolicyList) {
			ClaimPolicyDto dto = new ClaimPolicyDto();

			dto.setClaimId(c.getId());
			dto.setClaimAmount(c.getClaimAmount());
			dto.setClaimDate(c.getClaimDate().toString());
			dto.setClaimStatus(c.getClaimStatus().toString());
			dto.setPolicyType(c.getPolicy().getPolicyType().toString());
			dto.setPolicyCoverageAmount(c.getPolicy().getCoverageAmount());
			dto.setRegistrationNo(c.getPolicy().getVehicle().getRegistrationNo());

			String vehicleName = c.getPolicy().getVehicle().getManufacturerName() + " "
					+ c.getPolicy().getVehicle().getModelName();

			dto.setVehicleName(vehicleName);

			dtoList.add(dto);
		}
		// dtoList.size()+1 -> it is required to make pagination work properly
		Page<ClaimPolicyDto> pages = new PageImpl<ClaimPolicyDto>(dtoList, pageable, dtoList.size() + 1);

		return pages;
	}

	public long getNumberOfClaimsFiled(String customerUsername) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		return claimPolicyRepository.getNumberOfActivePolicies(customerId);
	}

//	public ClaimPolicy getClaimPolicy(int policyId, String customerUsername) throws PolicyNotClaimedException {
//		Customer customer = customerRepository.getCustomer(customerUsername);
//		int customerId = customer.getId();
//
//		ClaimPolicy claimPolicy = claimPolicyRepository.getStatusByCustomerPolicyId(customerPolicyId);
//		if (claimPolicy == null)
//			throw new PolicyNotClaimedException("You have not claimed this policy yet.");
//
//		String claimStatus = claimPolicy.getClaimStatus().toString();
//
//		logger.info("Getting policy claim status from DB " + claimStatus);
//		return claimStatus;
//	}

}