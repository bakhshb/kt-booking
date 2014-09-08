package com.kt.booking.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kt.booking.model.Account;
import com.kt.booking.service.AccountService;
import com.kt.booking.wrapper.AgentAccount;

@RestController
@RequestMapping("/rest/account")
public class AccountCtrl {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getCurrent(HttpServletRequest request) {
		String loginName = null;
		if (request.getUserPrincipal() != null) {
			loginName = request.getUserPrincipal().getName();
		}
		return loginName;
	}

	@RequestMapping(value = "/cookie", method = RequestMethod.GET)
	public Cookie setCookie(HttpServletRequest request) {
		String loginName = null;
		if (request.getUserPrincipal() != null) {
			loginName = request.getUserPrincipal().getName();
		}
		Cookie myCookie = new Cookie("username", loginName);
		return myCookie;
	}
	
	/**
	 * this method insert account
	 * 
	 * @param account
	 * @return Account and HttpStatus
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Account> save(@RequestBody final AgentAccount agentAccount) {
		return new ResponseEntity<Account>(accountService.save(agentAccount),HttpStatus.OK);
	}
	
}
