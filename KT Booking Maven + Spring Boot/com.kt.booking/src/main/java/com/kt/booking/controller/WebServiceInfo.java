package com.kt.booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServiceInfo {

	@RequestMapping("/rest/status")
	public String status() {
		return "Welcome to web service";
	}
	
	@RequestMapping("/rest/version")
	public String version() {
		return "Welcome to web service";
	}
}
