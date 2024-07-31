package com.zosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.exceptions.UserException;
import com.zosh.models.User;
import com.zosh.repository.UserRepository;
import com.zosh.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> allUser=userRepository.findAll();
		return allUser;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws UserException {
		
		User user=userService.findByUserId(id);
		return user;
	}
	
	

	
	@PutMapping("/api/users")
	public User updateUser(@RequestBody User user,@RequestHeader("Authorization") String jwt) throws UserException {
		
		User reqUser=userService.findUserByJwt(jwt);
		
		User updatedUser=userService.updateUser(user, reqUser.getId());
		return updatedUser;
	}
	
	/*
	 * no requirement of @PathVariable("userId1) if {userId1} and Integer userId1 will exactly match
	 */
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
		
		User reqUser=userService.findUserByJwt(jwt);
		User user=userService.followUser(reqUser.getId(), userId2);
		
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> serachUser(@RequestParam("query") String query){
		
		List<User> users=userService.searchUser(query);
		return users;
		
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		
//		System.out.println("jwt--------"+jwt);
		
		User user=userService.findUserByJwt(jwt);
		user.setPassword(null);
		
		return user;
		
	}

}
