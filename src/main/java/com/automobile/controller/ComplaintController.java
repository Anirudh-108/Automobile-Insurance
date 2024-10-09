package com.automobile.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.model.Complaint;
import com.automobile.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;

	@PostMapping("/add-complaint/{polcyId}")
	public Complaint addComplaint(@PathVariable int polcyId, @RequestBody Complaint complaint, Principal principal) {
		String customerUsername = principal.getName();
		return complaintService.addComplaint(polcyId, complaint, customerUsername);
	}

	@GetMapping("/all-complaints")
	public List<Complaint> getAllComplaints() {
		return complaintService.getAllComplaints();
	}
}
