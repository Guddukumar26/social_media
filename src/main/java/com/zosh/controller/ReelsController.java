package com.zosh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.models.Reels;
import com.zosh.models.User;
import com.zosh.service.ReelsService;
import com.zosh.service.UserService;

@RestController
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
		
		User user=userService.findUserByJwt(jwt);
		Reels CreatedReels=reelsService.createReel(reel, user);
		
		return CreatedReels;
		
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		
		List<Reels> reels=reelsService.findAllReels();
		
		return reels;
		
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception {
		
		List<Reels> reels=reelsService.findUsersReel(userId);
		
		return reels;
		
	}
}
