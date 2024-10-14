package com.automobile.controller;
<<<<<<< HEAD
=======

import java.security.Principal;

>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.model.UserInfo;
import com.automobile.repository.UserRepository;

@RestController
<<<<<<< HEAD
@CrossOrigin(origins = {"http://localhost:4200/"})
 public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello, User!";
    }
 
    @PostMapping("/auth/signup")
    public void signup(@RequestBody UserInfo userInfo) {
    	userInfo.setRole("ROLE_ADMIN"); 
    	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
    	userRepository.save(userInfo);
    }
 
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello, Admin!";
    }
=======
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/user/hello")
	public String userHello() {
		return "Hello, User!";
	}

	@PostMapping("/auth/signup")
	public void signup(@RequestBody UserInfo userInfo) {
		userInfo.setRole("ROLE_ADMIN");
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userRepository.save(userInfo);
	}

	@GetMapping("/admin/hello")
	public String adminHello() {
		return "Hello, Admin!";
	}

	@GetMapping("/auth/login")
	public UserInfo login(Principal principal) {
		String username = principal.getName();
		UserInfo user = userRepository.findByUsername(username).get();
		return user;
	}
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
}
