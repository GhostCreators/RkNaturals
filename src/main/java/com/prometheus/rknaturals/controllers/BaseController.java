package com.prometheus.rknaturals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prometheus.rknaturals.payments.PaytmPaymentService;

@RestController
public class BaseController {
	
	@Autowired
	PaytmPaymentService paytmPaymentService;
	
	@GetMapping("/")
	public String greeting() throws Exception {
		paytmPaymentService.initiateTransaction("cust","order1","10");
		return "ALIVE";
	}
	
	
}
