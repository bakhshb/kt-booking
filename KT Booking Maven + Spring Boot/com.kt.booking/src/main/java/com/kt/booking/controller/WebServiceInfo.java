package com.kt.booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServiceInfo {
	/**
	 * 
	 * @return welcome message
	 */
	@RequestMapping("/rest/status")
	public String status() {
		return "Welcome to web service";
	}

	/**
	 * 
	 * @return version Number
	 */
	@RequestMapping("/rest/version")
	public String version() {
		return "Welcome to web service";
	}
}
