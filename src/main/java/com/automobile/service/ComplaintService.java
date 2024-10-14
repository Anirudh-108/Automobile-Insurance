package com.automobile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.automobile.dto.ComplaintDto;
import com.automobile.model.Complaint;
=======
import com.automobile.dto.ComplainDto;
import com.automobile.model.Complain;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import com.automobile.model.Customer;
import com.automobile.model.Policy;
import com.automobile.repository.ComplaintRepository;
import com.automobile.repository.CustomerRepository;
import com.automobile.repository.PolicyRepository;

@Service
public class ComplaintService {
<<<<<<< HEAD
	
=======

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PolicyRepository policyRepository;

<<<<<<< HEAD
	public Complaint addComplaint(int polcyId, Complaint complaint, String customerUsername) {
=======
	public Complain addComplaint(int polcyId, Complain complaint, String customerUsername) {
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
		Customer customer = customerRepository.getCustomer(customerUsername);
		Policy policy = policyRepository.findById(polcyId).get();
		complaint.setCustomer(customer);
		complaint.setPolicy(policy);
		return complaintRepository.save(complaint);
	}

<<<<<<< HEAD
	public Page<ComplaintDto> getAllComplaints(String customerUsername, Pageable pageable) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		Page<Complaint> complainList = complaintRepository.getAllComplaints(customerId, pageable);

		List<ComplaintDto> dtoList = new ArrayList<>();

		for (Complaint c : complainList) {
			ComplaintDto dto = new ComplaintDto();
=======
	public Page<ComplainDto> getAllComplaints(String customerUsername, Pageable pageable) {
		Customer customer = customerRepository.getCustomer(customerUsername);
		int customerId = customer.getId();
		Page<Complain> complainList = complaintRepository.getAllComplaints(customerId, pageable);

		List<ComplainDto> dtoList = new ArrayList<>();

		for (Complain c : complainList) {
			ComplainDto dto = new ComplainDto();
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
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
<<<<<<< HEAD
		Page<ComplaintDto> pages = new PageImpl<ComplaintDto>(dtoList, pageable, dtoList.size()+1);
=======
		Page<ComplainDto> pages = new PageImpl<ComplainDto>(dtoList, pageable, dtoList.size()+1);
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

		return pages;
	}

}
