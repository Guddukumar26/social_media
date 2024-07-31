package com.zosh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String homeControllerHandler() {
		return "this is home controller";
	}
	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "this is home controller2";
	}
	
	@GetMapping("/codeWithZosh")
	public String homeControllerHandler3() {
		return "Hello code with zosh";
	}
	//@GetMapping ->To retrieve the data
	//@PutMapping -> To update the data
	//@PostMapping -> To add the data
	//@DeleteMapping ->to delete the data
}
