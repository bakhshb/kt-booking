package com.kt.booking.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/account")
public class AccountCtrl {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getCurrent(HttpServletRequest request) {
		String loginName = null;
		if (request.getUserPrincipal() != null) {
			 loginName = request.getUserPrincipal().getName();
		}
		return loginName;
	}
}
