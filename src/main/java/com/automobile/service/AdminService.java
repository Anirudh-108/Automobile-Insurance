package com.automobile.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.automobile.dto.CustomerVehicleDto;
import com.automobile.dto.ExecutivePerformanceDto;
import com.automobile.dto.PolicySummaryDto;
import com.automobile.dto.ReportDto;
import com.automobile.enums.ClaimStatus;
import com.automobile.enums.ComplaintStatus;
import com.automobile.enums.PolicyRequestStatus;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Complaint;
import com.automobile.model.Customer;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;
import com.automobile.model.UserInfo;
import com.automobile.model.Vehicle;
import com.automobile.repository.ClaimPolicyRepository;
import com.automobile.repository.ComplaintRepository;
import com.automobile.repository.CustomerPolicyRepository;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.ExecutiveRepository;
import com.automobile.repository.PolicyRepository;
import com.automobile.repository.UserRepository;
import com.automobile.repository.VehicleRepository;

@Service

public class AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExecutiveRepository executiveRepository;
    
    @Autowired
    private PolicyRepository policyRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    
    @Autowired
    private CustomerPolicyRepository customerPolicyRepository;
    
    @Autowired
    private ClaimPolicyRepository claimPolicyRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private ComplaintRepository complaintRepository;

    

    

    // Method to add a new executive
    public Executive addExecutive(Executive executive) {
        UserInfo user = executive.getUserInfo();
        user.setRole("ROLE_EXECUTIVE");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return executiveRepository.save(executive);
    }

    // Method to update an existing executive
    public Executive updateExecutive(int eid, Executive newExecutive) throws InvalidIdException {
        Optional<Executive> optional = executiveRepository.findById(eid);
        if (optional.isEmpty()) {
            throw new InvalidIdException("Invalid Executive ID: " + eid);
        }
        Executive executive = optional.get();
        executive.setName(newExecutive.getName());
        executive.setAge(newExecutive.getAge());
        executive.setGender(newExecutive.getGender());
        executive.setDepartment(newExecutive.getDepartment());
        executive.setEmail(newExecutive.getEmail());
        executive.setContactNumber(newExecutive.getContactNumber());
        executive.setCity(newExecutive.getCity());
        executive.setSalary(newExecutive.getSalary());

        return executiveRepository.save(executive);
    }

    // Method to delete an executive by ID
    public void deleteExecutive(int eid) throws InvalidIdException {
        Optional<Executive> optional = executiveRepository.findById(eid);
        if (optional.isEmpty()) {
            throw new InvalidIdException("Invalid Executive ID: " + eid);
        }
        executiveRepository.deleteById(eid);
    }

    // Method to get all executives
    public List<Executive> getAllExecutives() {
        return executiveRepository.findAll();
    }

    // Method to get an executive by ID (if needed)
    public Executive getExecutiveById(int eid) throws InvalidIdException {
        Optional<Executive> optional = executiveRepository.findById(eid);
        if (optional.isEmpty()) {
            throw new InvalidIdException("Invalid Executive ID: " + eid);
        }
        return optional.get();
    }
    
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    
    public List<CustomerPolicy> findPoliciesByCustomerId(int customerId) {
        return customerPolicyRepository.findByCustomerId(customerId); 
    }
    
   
	
    // Method to approve a policy by executive--by executive
    public CustomerPolicy approvePolicy(int executiveId, int policyId) throws InvalidIdException {
        Optional<Executive> executiveOptional = executiveRepository.findById(executiveId);
        if (executiveOptional.isEmpty()) {
            throw new InvalidIdException("Invalid Executive ID: " + executiveId);
        }
        Executive executive = executiveOptional.get();

        Optional<CustomerPolicy> policyOptional = customerPolicyRepository.findById(policyId);
        if (policyOptional.isEmpty()) {
            throw new InvalidIdException("Policy not found with id: " + policyId);
        }
        CustomerPolicy customerPolicy = policyOptional.get();

        // Update policy status and assign the executive
        customerPolicy.setPolicyRequestStatus(PolicyRequestStatus.Approved);
        customerPolicy.setExecutive(executive);
        return customerPolicyRepository.save(customerPolicy);
    }
    
    
    public List<PolicySummaryDto> getPoliciesHandledByExecutive(int executiveId) throws InvalidIdException {
        List<CustomerPolicy> policies = customerPolicyRepository.getPoliciesHandledByExecutive(executiveId);
        
        if (policies.isEmpty()) {
            throw new InvalidIdException("No executive found with ID: " + executiveId);
        }

        List<PolicySummaryDto> policySummaryList = policies.stream().map(policy ->
        	new PolicySummaryDto(
        			policy.getCustomer().getName(),
        			policy.getPolicy().getPolicyType(),
        			policy.getPolicy().getPremiumAmount(),
        			policy.getPolicy().getCoverageAmount(),
        			policy.getPolicy().getTermLength(),
        			policy.getPolicy().getPolicyStatus(),
        			policy.getPolicy().getId()
        			)
        ).collect(Collectors.toList());

        return policySummaryList;
    }

    public List<ExecutivePerformanceDto> getAllExecutivesPerformanceReport() {
        List<Executive> executives = executiveRepository.findAll();

        List<ExecutivePerformanceDto> performanceReports = new ArrayList<>();
        for (Executive executive : executives) {
            List<CustomerPolicy> customerPolicies = customerPolicyRepository.findByExecutive(executive);
            List<ClaimPolicy> claimPolicies = claimPolicyRepository.findByExecutive(executive);

            long policiesApproved = customerPolicies.stream()
                .filter(cp -> cp.getPolicyRequestStatus() == PolicyRequestStatus.Approved)
                .count();

            long policiesCancelled = customerPolicies.stream()
                .filter(cp -> cp.getPolicyRequestStatus() == PolicyRequestStatus.Cancelled)
                .count();

            long policiesRequested = customerPolicies.stream()
                .filter(cp -> cp.getPolicyRequestStatus() == PolicyRequestStatus.Requested)
                .count();

            long claimsApproved = claimPolicies.stream()
                .filter(clp -> clp.getClaimStatus() == ClaimStatus.Approved)
                .count();

            long claimsDenied = claimPolicies.stream()
                .filter(clp -> clp.getClaimStatus() == ClaimStatus.Denied)
                .count();

            long claimsPending = claimPolicies.stream()
                .filter(clp -> clp.getClaimStatus() == ClaimStatus.Pending)
                .count();

            ExecutivePerformanceDto report = new ExecutivePerformanceDto(
                executive.getId(),
                executive.getName(),
                (int) policiesApproved,
                (int) policiesCancelled,
                (int) claimsApproved,
                (int) claimsDenied,
                (int) policiesRequested,
                (int) claimsPending
            );
            performanceReports.add(report);
        }

        return performanceReports;
    }
    
    
  public ReportDto generateReport() {
	  List<Object[]> reportData = customerPolicyRepository.generateReport();  
		
	    ReportDto report = new ReportDto();

	    if (!reportData.isEmpty()) {
	        Object[] data = reportData.get(0); 
	        report.setPolicyCount(((Number) data[0]).longValue());
	        report.setMostCommonPolicyType((String) data[1]);
	        report.setCustomerCount(((Number) data[2]).longValue());
	        report.setExecutiveCount(((Number) data[3]).longValue());
	        report.setHighestPremium(((Number) data[4]).doubleValue());
	    }

	    return report;
  }
  
  public List<ClaimPolicy>getAllClaims(){
		List<ClaimPolicy>allclaims=claimPolicyRepository.findAll();
		return allclaims;
	}
  
  public List<CustomerVehicleDto> getCustomerPolicyAndVehicleDetails(int customerId) {
      // Retrieve all customer policies and vehicles for the given customer ID
      List<CustomerPolicy> customerPolicies = customerPolicyRepository.findByCustomerId(customerId);
      List<Vehicle> vehicles = vehicleRepository.findByCustomerId(customerId);

      // Create a list to hold the combined DTOs
      List<CustomerVehicleDto> policyVehicleDetails = new ArrayList<>();

      for (CustomerPolicy policy : customerPolicies) {
          for (Vehicle vehicle : vehicles) {
           
              CustomerVehicleDto dto = new CustomerVehicleDto(
                  policy.getPolicy().getId(),
                  policy.getPolicy().getPolicyType(),
                  policy.getBuyingDate(),
                  vehicle.getVehicleType(),
                  vehicle.getManufacturerName(),
                  vehicle.getModelName(),
                  vehicle.getYearOfPurchase()
              );
              policyVehicleDetails.add(dto);
          }
      }

      return policyVehicleDetails;
  }
  
  public List<CustomerPolicy> getCurrentMonthPolicies(int executiveId) {
      YearMonth currentMonth = YearMonth.now();
      LocalDate startDate = currentMonth.atDay(1);
      LocalDate endDate = currentMonth.atEndOfMonth();
      return customerPolicyRepository.findPoliciesByExecutiveAndDateRange(executiveId, startDate, endDate);
  }

  public List<CustomerPolicy> getPreviousMonthPolicies(int executiveId) {
      YearMonth previousMonth = YearMonth.now().minusMonths(1);
      LocalDate startDate = previousMonth.atDay(1);
      LocalDate endDate = previousMonth.atEndOfMonth();
      return customerPolicyRepository.findPoliciesByExecutiveAndDateRange(executiveId, startDate, endDate);
  }
  
  public List<Complaint> getAllComplaints() {
	    return complaintRepository.findAll(); 
	}
  
  public Complaint resolveComplaint(int id) {
      Complaint complaint = complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
      
      // Update the status of the complaint
      complaint.setComplaintStatus(ComplaintStatus.Resolved);
      return complaintRepository.save(complaint);
  }
}
