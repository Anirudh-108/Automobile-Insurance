package com.automobile.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.automobile.JwtUtil;
import com.automobile.dto.TokenDto;
import com.automobile.model.UserInfo;
import com.automobile.repository.UserRepository;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/auth/token")



    public TokenDto createAuthenticationToken(@RequestBody UserInfo authenticationRequest,TokenDto dto) throws Exception {


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

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
    }
}
