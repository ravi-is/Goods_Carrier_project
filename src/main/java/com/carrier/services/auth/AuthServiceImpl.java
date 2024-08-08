package com.carrier.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrier.dto.SignupRequest;
import com.carrier.dto.Userdto;
import com.carrier.entity.User;
import com.carrier.enums.UsersRole;
import com.carrier.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
public class AuthServiceImpl implements AuthService {
	
private final UserRepository userRepository;
@Autowired
public AuthServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
}

@Override
public Userdto createCustomer(SignupRequest signupRequest) {
	// TODO Auto-generated method stub
	User user=new User();
	user.setName(signupRequest.getName());
	user.setEmail(signupRequest.getEmail());
	user.setPassword(signupRequest.getPassword());
	user.setUsersRole(UsersRole.CUSTOMER);
	User createUser = userRepository.save(user);
	Userdto userdto=new Userdto();
	userdto.setId(createUser.getId());
	return userdto;
}

@Override
public boolean hasCustomerEmail(String email) {
	// TODO Auto-generated method stub
	return userRepository.findFirstByEmail(email).isPresent();
}
}
