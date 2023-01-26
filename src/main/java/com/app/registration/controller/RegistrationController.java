package com.app.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.registration.model.User;
import com.app.registration.service.RegistrationService;

@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")

	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		if (tempEmailId != null && !"".equals(tempEmailId)) {
			User userobj=service.fetchUserByEmailId(tempEmailId);
			if(userobj != null) {
				throw new Exception("User with "+tempEmailId+" is already exist");
			}
		}
		User userObj = null;
		userObj = service.saveUser(user);
		return userObj;
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		String tempEmailId = user.getEmailId();
		String tempPass = user.getPassword();
		User userObj = null;
		if(tempEmailId != null && tempPass != null) {
			userObj = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}
		if(userObj == null) {
			throw new Exception("bad credential");
		}
		return userObj;
		
	}

}
