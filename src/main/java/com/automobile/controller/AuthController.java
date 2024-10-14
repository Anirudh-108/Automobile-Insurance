package com.automobile.controller;

<<<<<<< HEAD
import java.security.Principal;

=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.JwtUtil;
import com.automobile.dto.TokenDto;
import com.automobile.model.UserInfo;
<<<<<<< HEAD
import com.automobile.repository.UserRepository;
import com.automobile.service.MyUserDetailsService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
=======
import com.automobile.service.MyUserDetailsService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;
<<<<<<< HEAD
    
    @Autowired
    private UserRepository userRepository;
=======
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth/token")
<<<<<<< HEAD
    public TokenDto createAuthenticationToken(@RequestBody UserInfo authenticationRequest, TokenDto dto) throws Exception {
=======
    public TokenDto createAuthenticationToken(@RequestBody UserInfo authenticationRequest,TokenDto dto) throws Exception {
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
<<<<<<< HEAD
        System.out.println(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        dto.setToken(jwt);
        return dto;
    }
    
    @GetMapping("/auth/login")
    public UserInfo login(Principal principal) {
    	String username = principal.getName();
    	UserInfo info = userRepository.findByUsername(username).get();
    	return info; 
=======
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        dto.setToken(jwt);
		return dto;
>>>>>>> 9bbbd5c0f59209cb4ece85113afbb61cb92ba005
    }
}
