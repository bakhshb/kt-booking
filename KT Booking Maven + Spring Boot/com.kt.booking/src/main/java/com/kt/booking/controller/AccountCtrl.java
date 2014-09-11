package com.kt.booking.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kt.booking.model.Account;
import com.kt.booking.model.Agent;
import com.kt.booking.repository.AgentRepository;
import com.kt.booking.service.AccountService;
import com.kt.booking.wrapper.AgentAccount;

@RestController
@RequestMapping("/rest/account")
public class AccountCtrl {

	@Autowired
	private AccountService accountService;
	@Autowired
	private AgentRepository agentRepository;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<String> getCurrent(HttpServletRequest request) {
		String loginName = null;
		// create header response
		HttpHeaders responseHeaders = new HttpHeaders();

		if (request.getUserPrincipal() != null) {
			loginName = request.getUserPrincipal().getName();
			return new ResponseEntity<String>(loginName, HttpStatus.OK);
		} else {
			responseHeaders.add("MyResponseHeader", "unauthenticated ");
			return new ResponseEntity<String>(responseHeaders, HttpStatus.NOT_ACCEPTABLE);
		}

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
	public ResponseEntity<Account> save(
			@RequestBody final AgentAccount agentAccount) {
		// check username is not exist
		Account account = accountService.findByUserName(agentAccount
				.getUserName());
		// check email is not exist
		Agent agent = agentRepository.findByEmail(agentAccount.getEmail());
		// create header response
		HttpHeaders responseHeaders = new HttpHeaders();

		if (account != null) {
			responseHeaders
					.set("MyResponseHeader", "UserName is already exist");
			return new ResponseEntity<Account>(responseHeaders,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (agent != null) {
			responseHeaders.set("MyResponseHeader", "Email is already exist");
			return new ResponseEntity<Account>(responseHeaders,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Account>(
					accountService.save(agentAccount), HttpStatus.OK);
		}
	}

}
