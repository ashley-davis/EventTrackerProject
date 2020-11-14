package com.skilldistillery.dogwalker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.dogwalker.services.DogwalkerService;

@RequestMapping("api")
@RestController
public class DogwalkerController {
	
	@Autowired
	private DogwalkerService svc;
	
	@GetMapping("ping")
	public String ping() {
		return "pong!";
	}

}
