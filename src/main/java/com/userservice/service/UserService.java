package com.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.entity.User;
import com.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getUserAndItsDepartment(Long userId) {
		return userRepository.findByUserId(userId);
	}

}
