package com.automobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automobile.dto.ComplainDto;
import com.automobile.model.Complain;
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

	public Complain addComplaint(int polcyId, Complain complaint, String customerUsername) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		Policy policy = policyRepository.findById(polcyId).get();
		complaint.setCustomer(customer);
		complaint.setPolicy(policy);
		return complaintRepository.save(complaint);
	}

	public Page<ComplainDto> getAllComplaints(String customerUsername, Pageable pageable) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		Page<Complain> complainList = complaintRepository.getAllComplaints(customerId, pageable);

		List<ComplainDto> dtoList = new ArrayList<>();

		for (Complain c : complainList) {
			ComplainDto dto = new ComplainDto();
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
		Page<ComplainDto> pages = new PageImpl<ComplainDto>(dtoList, pageable, dtoList.size()+1);

		return pages;
	}

}
