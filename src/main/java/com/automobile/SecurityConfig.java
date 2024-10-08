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
                        .requestMatchers("/customer/getName/{username}").permitAll()
                        .requestMatchers("/customer/getCustomer/{username}").permitAll()
                        
                        .requestMatchers("/customerDetails/add").hasRole("CUSTOMER")
                        .requestMatchers("/customerDetails/upload/documents").hasRole("CUSTOMER")
                        
                        .requestMatchers("/policy/showPolicy/{policyType}").permitAll()
                        .requestMatchers("/policy/getPolicy/{policyType}").permitAll()
                        .requestMatchers("/policy/buy/{policyType}").hasRole("CUSTOMER")
                        
                        .requestMatchers("/claim/showAll").hasRole("CUSTOMER")
                        .requestMatchers("/claim/one/{policyId}").hasRole("CUSTOMER")
                        .requestMatchers("/claim/status/{policyId}").hasRole("CUSTOMER")
                        
                        .requestMatchers("/renewal/showAll").hasRole("CUSTOMER")
                        .requestMatchers("/renewal/renew/{policyId}").hasRole("CUSTOMER")
                        
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