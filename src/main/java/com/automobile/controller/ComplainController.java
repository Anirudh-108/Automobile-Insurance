package com.automobile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.dto.ComplainDto;
import com.automobile.model.Complain;
import com.automobile.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ComplainController {

	@Autowired
	private ComplaintService complaintService;

	@PostMapping("/add-complaint/{polcyId}")
	public Complain addComplaint(@PathVariable int polcyId, @RequestBody Complain complaint, Principal principal) {
		String customerUsername = principal.getName();
		return complaintService.addComplaint(polcyId, complaint, customerUsername);
	}

	@GetMapping("/all-complaints")
	public Page<ComplainDto> getAllProducts(@RequestParam(defaultValue = "0", required = false) Integer page,
			@RequestParam(defaultValue = "100000", required = false) Integer size,Principal principal) {
		String customerUsername = principal.getName();
		
		Pageable pageable = PageRequest.of(page, size);		
		return complaintService.getAllComplaints(customerUsername,pageable);
	}
}
