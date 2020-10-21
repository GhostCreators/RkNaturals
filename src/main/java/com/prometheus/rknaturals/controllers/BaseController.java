package com.prometheus.rknaturals.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prometheus.rknaturals.payments.PaytmPaymentService;
import com.prometheus.rknaturals.util.Encryptor;

@RestController("/api")
public class BaseController {
	
	@Autowired
	PaytmPaymentService paytmPaymentService;
	
	@GetMapping("/")
	public String greeting() throws Exception {
		paytmPaymentService.initiateTransaction("cust","order1","10");
		return "ALIVE";
	}
	
	@GetMapping("/pay")
	public ModelAndView payment() throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payment");
          
        modelAndView.getModel().put("url",paytmPaymentService.getPaymentUrl("order4"));
        
        modelAndView.getModel().put("mid",paytmPaymentService.getMid());
        modelAndView.getModel().put("orderId","order4");
        modelAndView.getModel().put("txnToken",paytmPaymentService.initiateTransaction("cust","order4","10"));
        
        return modelAndView;
		
	}
	
}
