package com.lonix.det.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {

	@GetMapping("/")
	public String cool(){
		return "What The Fuck";
	}
}
