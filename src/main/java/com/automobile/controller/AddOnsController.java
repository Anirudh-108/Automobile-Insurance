package com.automobile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/add-ons")
public class AddOnsController {
	@PostMapping("/add")
	public void addAddOns() {

	}

	@GetMapping("/show")
	public void showAddOns() {

	}
}
