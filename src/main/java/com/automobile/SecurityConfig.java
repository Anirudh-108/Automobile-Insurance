package com.automobile;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/token").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/signup").permitAll()
                        
                        .requestMatchers("/customer/add").permitAll()
                        .requestMatchers("/customerDetails/add").hasRole("CUSTOMER")
                        .requestMatchers("/customerDocuments/upload").hasRole("CUSTOMER")
                        .requestMatchers("/customerDetails/upload/documents").hasRole("CUSTOMER") ///vehicleDocuments/upload
                        .requestMatchers("/vehicleDocuments/upload/{modelName}").hasRole("CUSTOMER")
                        
                        .requestMatchers("/policy/showPolicy/{policyType}").permitAll()
                        .requestMatchers("/policy/buy/{policyType}").hasRole("CUSTOMER")
                        
                        .requestMatchers("/claim/showAll").hasRole("CUSTOMER")
                        .requestMatchers("/claim/one/{policyId}").hasRole("CUSTOMER")
                        .requestMatchers("/claim/status/{policyId}").hasRole("CUSTOMER")
                        
                        .requestMatchers("/renewal/showAll").hasRole("CUSTOMER")
                        .requestMatchers("/renewal/renew/{policyId}").hasRole("CUSTOMER")
                        
                        .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/executive-performance/{executiveId}").permitAll()
                        
                        .requestMatchers("/customer/all").permitAll()
                        .requestMatchers("/executive/policies/all").permitAll()
                        .requestMatchers("/executive/details").hasRole("EXECUTIVE")
                        .requestMatchers("/executive/policy-status").permitAll()
                        .requestMatchers("/executive/claim-status").permitAll()
                        .requestMatchers("/executive/claims/all").permitAll()
                        .requestMatchers("/executive/policies/{customerId}").permitAll()
                        .requestMatchers("/executive/policy-details/{policyId}").permitAll()
                        .requestMatchers("/executive/policies/status/{policyRequestStatus}").hasAnyRole("EXECUTIVE","ADMIN")
                        .requestMatchers("/executive/policy/{policyId}/request-status").permitAll()//customer_policy id
                        .requestMatchers("/executive/claim/status").hasAnyRole("EXECUTIVE","ADMIN")
                        .requestMatchers("/claim/claim-details/{id}").permitAll()
                        .requestMatchers("/executive/delete/executive/{eid}").hasAnyRole("EXECUTIVE","ADMIN")
                        .requestMatchers("/executive/policy/{claimpolicyId}/status").permitAll()
                        .requestMatchers("/vehicleDocuments/vehicle/{vehicleId}/documents").permitAll()
                        
                        .requestMatchers("/complaint/add-complaint/{policyId}").permitAll()
                        .requestMatchers("/executive/viewall-complaints").permitAll()
                        

                        .requestMatchers("/add/executives").hasRole("ADMIN")
                        .requestMatchers("/customer/details").hasRole("ADMIN")
                        .requestMatchers("/update/executive/{eid}").hasRole("ADMIN")
                        .requestMatchers("/delete/executive/{eid}").hasRole("ADMIN")
                        .requestMatchers("/executives").hasRole("ADMIN")
                        .requestMatchers("/executive/{id}").hasRole("ADMIN")
                        .requestMatchers("/{executiveId}/policies").hasRole("ADMIN")
                        .requestMatchers("all/executive-performance").hasRole("ADMIN")
                        .requestMatchers("/report").hasRole("ADMIN")
                        .requestMatchers("/claims/all").hasRole("ADMIN")
                        .requestMatchers("/view/policy/details/{customerId}").hasRole("ADMIN")
                        .requestMatchers("/reports/{executiveId}/current-month").hasRole("ADMIN")
                        .requestMatchers("/reports/{executiveId}/previous-month").hasRole("ADMIN")
                        .requestMatchers("/viewall-complaints/admin").hasRole("ADMIN")
                        .requestMatchers("/complaints/resolve/{id}").hasRole("ADMIN")
                        .requestMatchers("/customer/update").permitAll()
                        .requestMatchers("/customer/getName/{username}").permitAll()
                        .requestMatchers("/customer/getCustomer/{username}").permitAll()
                        
                        .requestMatchers("/vehicle/add").permitAll()
                        
                        .requestMatchers("/customerDocuments/upload").permitAll()
                        
                        .requestMatchers("/vehicleDocuments/upload/{registrationNo}").permitAll()
                        
                        .requestMatchers("/complaint/add-complaint/{polcyId}").permitAll()
                        .requestMatchers("/complaint/all-complaints").permitAll()
                        
                        .requestMatchers("/policy/showPolicy/{policyType}").permitAll()
                        .requestMatchers("/policy/getPolicy/{policyType}").permitAll()
                        .requestMatchers("/policy/buy/{policyType}").permitAll()
                        .requestMatchers("/policy/getAllActivePolicies").permitAll()
                        .requestMatchers("/policy/getAllExpiredPolicies").permitAll()
                        .requestMatchers("/policy/getActivePolicy/{policyId}").permitAll()
                        .requestMatchers("/policy/getNumberOfActivePolicies").permitAll()
                        .requestMatchers("/policy/getNumberOfExpiredPolicies").permitAll()
                        .requestMatchers("/policy/getVehicleByPolicyId/{policyId}").permitAll()
                        .requestMatchers("/policy/getPolicyById/{policyId}").permitAll()
                        
                        .requestMatchers("/claim/showAll").hasRole("CUSTOMER")
                        .requestMatchers("/claim/one/{policyId}").hasRole("CUSTOMER")
                        .requestMatchers("/claim/all-claims").permitAll()
                        .requestMatchers("/claim/getNumberOfClaimsFiled").permitAll()  
                        
                        .requestMatchers("/renewal/showAll").hasRole("CUSTOMER")
                        .requestMatchers("/renewal/renew/{policyId}").permitAll()
                        
                        .requestMatchers("/user/hello").hasAnyRole("USER", "ADMIN")
                        
                        .requestMatchers("/executive/policies/customerId").hasAnyRole("EXECUTIVE", "ADMIN")
                        .requestMatchers("/executive/policies/status/{policyRequestStatus}").hasAnyRole("EXECUTIVE","ADMIN")
                        .requestMatchers("/executive/policy/{policyId}/request-status").hasAnyRole("EXECUTIVE", "ADMIN")
                        .requestMatchers("/executive/claim/status").hasAnyRole("EXECUTIVE","ADMIN")
                        .requestMatchers("/executive/policy/{claimpolicyId}/status").hasAnyRole("EXECUTIVE","ADMIN")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
     AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
     DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}