package com.carrier.services.auth;

import com.carrier.dto.SignupRequest;
import com.carrier.dto.Userdto;

public interface AuthService {

	Userdto createCustomer(SignupRequest signupRequest);
	
	boolean hasCustomerEmail(String email);
}
