package com.carrier.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrier.dto.SignupRequest;
import com.carrier.dto.Userdto;
import com.carrier.services.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;
    

    public AuthController(AuthService authService) {
		this.authService = authService;
	}


	@PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
		if(authService.hasCustomerEmail(signupRequest.getEmail()))
			return new ResponseEntity<>("customer is alredy exist with this email",HttpStatus.NOT_ACCEPTABLE);
        Userdto createCustomeDto = authService.createCustomer(signupRequest);
        if(createCustomeDto == null) 
            return new ResponseEntity<>("customer not created, come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createCustomeDto, HttpStatus.CREATED);
    }
}
