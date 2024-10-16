package com.automobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automobile.dto.ComplaintDto;
import com.automobile.model.Complaint;
import com.automobile.model.Customer;
import com.automobile.model.Policy;
import com.automobile.repository.ComplaintRepository;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.PolicyRepository;

@Service
public class ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PolicyRepository policyRepository;

	public Complaint addComplaint(int polcyId, Complaint complaint, String customerUsername) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		Policy policy = policyRepository.findById(polcyId).get();
		complaint.setCustomer(customer);
		complaint.setPolicy(policy);
		return complaintRepository.save(complaint);
	}

	public Page<ComplaintDto> getAllComplaints(String customerUsername, Pageable pageable) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		Page<Complaint> complainList = complaintRepository.getAllComplaints(customerId, pageable);

		List<ComplaintDto> dtoList = new ArrayList<>();

		for (Complaint c : complainList) {
			ComplaintDto dto = new ComplaintDto();
			dto.setComplaintId(c.getId());
			dto.setComplaintStatus(c.getComplaintStatus().toString());
			dto.setComplaintType(c.getComplaintType().toString());
			dto.setDescription(c.getDescription());
			dto.setPolicyType(c.getPolicy().getPolicyType().toString());

			String vehicleName = c.getPolicy().getVehicle().getManufacturerName() + " "
					+ c.getPolicy().getVehicle().getModelName();

			dto.setVehcleName(vehicleName);

			dtoList.add(dto);
		}

		// dtoList.size()+1 -> it is required to make pagination work properly
		Page<ComplaintDto> pages = new PageImpl<ComplaintDto>(dtoList, pageable, dtoList.size()+1);

		return pages;
	}

}
