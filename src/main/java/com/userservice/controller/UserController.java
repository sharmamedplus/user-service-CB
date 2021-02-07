package com.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.userservice.entity.User;
import com.userservice.service.UserService;
import com.userservice.vo.Department;
import com.userservice.vo.ResponseTemplateVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/{userId}")
	public ResponseTemplateVO getUserAndItsDepartment(@PathVariable("userId") Long userId) {
		log.info("UserController");
		ResponseTemplateVO response = new ResponseTemplateVO();
		User user = userService.getUserAndItsDepartment(userId);
		response.setUser(user);
		//Before Service Registry 
		//response.setDepartment(restTemplate.getForObject("http://localhost:2020/departments/"+user.getDepartmentId(), Department.class));
		// After Service Registry
		response.setDepartment(restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class));
		return response;
	}
}
