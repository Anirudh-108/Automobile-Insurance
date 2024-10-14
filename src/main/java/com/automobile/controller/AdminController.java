package com.automobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.CustomerVehicleDto;
import com.automobile.dto.ExecutivePerformanceDto;
import com.automobile.dto.MessageDto;
import com.automobile.dto.PolicySummaryDto;
import com.automobile.dto.ReportDto;
import com.automobile.exception.InvalidIdException;
import com.automobile.model.ClaimPolicy;
import com.automobile.model.Complaint;
import com.automobile.model.Customer;
import com.automobile.model.CustomerPolicy;
import com.automobile.model.Executive;
import com.automobile.service.AdminService;

@RestController
@CrossOrigin(origins= {"http://localhost:4200/"})
public class AdminController {

	 @Autowired
	 private AdminService adminService;

 
    @PostMapping("/add/executives")
    public ResponseEntity<Executive> addExecutive(@RequestBody Executive executive) {
        Executive savedExecutive = adminService.addExecutive(executive);
        return ResponseEntity.ok(savedExecutive);
    }
    
    @GetMapping("/customer/details")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = adminService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }
    
    @PutMapping("/update/executive/{eid}")
    public ResponseEntity<?> updateExecutive(@PathVariable int eid, @RequestBody Executive newExecutive, MessageDto dto) {
        try {
            Executive updatedExecutive = adminService.updateExecutive(eid, newExecutive);
            return ResponseEntity.ok(updatedExecutive);
        } catch (InvalidIdException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    @DeleteMapping("/delete/executive/{eid}")
    public ResponseEntity<MessageDto> deleteExecutive(@PathVariable int eid, MessageDto dto) {
        try {
            adminService.deleteExecutive(eid);
            dto.setMsg("Executive deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (InvalidIdException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }


    @GetMapping("/executives")
    public ResponseEntity<List<Executive>> getAllExecutives() {
        List<Executive> executives = adminService.getAllExecutives();
        return ResponseEntity.ok(executives);
    }


    @GetMapping("/executive/{eid}")
    public ResponseEntity<?> getExecutiveById(@PathVariable int eid) {
        try {
            Executive executive = adminService.getExecutiveById(eid);
            return ResponseEntity.ok(executive);
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
 

    //  for approving a policy by executive---done by executive
    @PutMapping("/{executiveId}/approvePolicy/{policyId}")
    public ResponseEntity<CustomerPolicy> approvePolicy(
            @PathVariable int executiveId, 
            @PathVariable int policyId) throws InvalidIdException {
        CustomerPolicy updatedPolicy = adminService.approvePolicy(executiveId, policyId);
        return ResponseEntity.ok(updatedPolicy);
    }
    
    

    @GetMapping("/{executiveId}/policies")
    public ResponseEntity<List<PolicySummaryDto>> getPoliciesForExecutive(@PathVariable int executiveId) {
        try {
            List<PolicySummaryDto> policies = adminService.getPoliciesHandledByExecutive(executiveId);
            return ResponseEntity.ok(policies);
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
     

    @GetMapping("all/executive-performance")
    public ResponseEntity<?> getAllExecutivesPerformanceReport() throws InvalidIdException {
        List<ExecutivePerformanceDto> executivePerformances = adminService.getAllExecutivesPerformanceReport();
		return ResponseEntity.ok(executivePerformances);
    }
    
   @GetMapping("/report")
   public ResponseEntity<?> getReport() {
       ReportDto report = adminService.generateReport();
       return ResponseEntity.ok(report);
   }
    
   //fetch all claims
 	@GetMapping("/claims/all")
 	public List<ClaimPolicy>getallClaims(){
 		List<ClaimPolicy>allclaims=adminService.getAllClaims();
 		return allclaims;
 	}
 	
 	@GetMapping("/view/policy/details/{customerId}")
    public ResponseEntity<List<CustomerVehicleDto>> getCustomerPolicyAndVehicleDetails(@PathVariable int customerId) {
        List<CustomerVehicleDto> policyVehicleDetails = adminService.getCustomerPolicyAndVehicleDetails(customerId);
        return ResponseEntity.ok(policyVehicleDetails);
    }
 	
 	@GetMapping("/reports/{executiveId}/current-month")
    public List<CustomerPolicy> getCurrentMonthReport(@PathVariable int executiveId) {
        return adminService.getCurrentMonthPolicies(executiveId);
    }

    @GetMapping("/reports/{executiveId}/previous-month")
    public List<CustomerPolicy> getPreviousMonthReport(@PathVariable int executiveId) {
        return adminService.getPreviousMonthPolicies(executiveId);
    }
    
    @GetMapping("/viewall-complaints/admin")
    public List<Complaint> getAllComplaints() {
        return adminService.getAllComplaints();  
    }
    
    @PutMapping("/complaints/resolve/{id}")
    public ResponseEntity<?> resolveComplaint(@PathVariable int id) {
            Complaint updatedComplaint = adminService.resolveComplaint(id);
            return ResponseEntity.ok(updatedComplaint);
        
    }
}
