package com.automobile.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automobile.enums.ClaimStatus;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.enums.PolicyStatus;
import com.automobile.enums.VehicleClaimCondition;
import com.automobile.enums.VehicleType;
import com.automobile.exception.CannotClaimPolicyException;
import com.automobile.exception.InvalidIdException;
import com.automobile.exception.PolicyNotClaimedException;
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

//	@Autowired
//	private PolicyRepository policyRepository;

	public List<Policy> getAllActivePolicy(String customerUsername) {
		Customer customerDB = customerRepository.getCustomer(customerUsername);
		int customerId = customerDB.getId();
		return claimPolicyRepository.findPolicyByStatus(customerId, PolicyStatus.Active);
	}

	public ClaimPolicy claimPolicy(int policyId, String customerUsername, ClaimDetails claimDocuments)
			throws CannotClaimPolicyException {

		CustomerPolicy customerPolicy = customerPolicyRepository.getCustomerPolicyByPolicyId(policyId,
				customerUsername);

		if (customerPolicy.getPolicyRequestStatus().toString()
				.equalsIgnoreCase(PolicyRequestStatus.Requested.toString())) {
			throw new CannotClaimPolicyException("Cannot claim this policy as this policy is not approved yet.");
		}
		if (customerPolicy.getPolicyRequestStatus().toString()
				.equalsIgnoreCase(PolicyRequestStatus.Cancelled.toString())) {
			throw new CannotClaimPolicyException("Cannot claim this policy as this policy is cancelled.");
		}

//		Policy policy = policyRepository.findById(policyId).get();
//		policy.setPolicyStatus(PolicyStatus.Claimed);
//		policyRepository.save(policy);

		int customerId = customerPolicy.getCustomer().getId();

		double claimAmount = calculateClaimAmount(customerId, claimDocuments);
		System.out.println(claimAmount);

		claimDocuments = claimDetailsRepository.save(claimDocuments);

		ClaimPolicy claimPolicy = new ClaimPolicy();

		claimPolicy.setClaimDocuments(claimDocuments);

		claimPolicy.setClaimAmount(claimAmount);
		claimPolicy.setClaimDate(LocalDate.now());
		claimPolicy.setClaimStatus(ClaimStatus.Pending);
		claimPolicy.setCustomerPolicy(customerPolicy);

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
	public double calculateClaimAmount(int customerId, ClaimDetails claimDetails) {

		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.get();
		int custId = customer.getId();

		Vehicle vehicle = vehicleRepository.getVehicleByRegistrationNo(custId, claimDetails.getRegistrationNo());

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

	public String claimPolicyStatus(int policyId, String customerUsername) throws PolicyNotClaimedException {
		CustomerPolicy customerPolicy = customerPolicyRepository.getCustomerPolicyByPolicyId(policyId,
				customerUsername);
		int customerPolicyId = customerPolicy.getId();

		ClaimPolicy claimPolicy = claimPolicyRepository.getStatusByCustomerPolicyId(customerPolicyId);
		if (claimPolicy == null)
			throw new PolicyNotClaimedException("You have not claimed this policy yet.");
		String claimStatus = claimPolicy.getClaimStatus().toString();
		return claimStatus;
	}
	
	
	//Executive
	
	// Fetch details of a policy-claim by claim id
	public ClaimPolicy getClaimDetails(int id) throws InvalidIdException {
		ClaimPolicy claimDetails = claimPolicyRepository.findById(id).get();
		return claimDetails;
	}

}