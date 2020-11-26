package com.prometheus.rknaturals.controllers;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.prometheus.rknaturals.payments.PaytmPaymentService;
import com.prometheus.rknaturals.util.Encryptor;

//@RestController
//@RequestMapping("/api")
public class PaymentController {

	@Autowired
	PaytmPaymentService paytmPaymentService;

	@GetMapping("/")
	public String greeting() throws Exception {
		paytmPaymentService.initiateTransaction("cust", "order1", "10");
		return "ALIVE";
	}

	@GetMapping("/pay")
	public ModelAndView payment() throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("payment");

		modelAndView.getModel().put("url", paytmPaymentService.getPaymentUrl("order5"));

		modelAndView.getModel().put("mid", paytmPaymentService.getMid());
		modelAndView.getModel().put("orderId", "order5");
		modelAndView.getModel().put("txnToken", paytmPaymentService.initiateTransaction("cust", "order5", "1"));

		return modelAndView;

	}

	@PostMapping(value = "/callback")
	public String getResponseRedirect(HttpServletRequest request) {

		Map<String, String[]> mapData = request.getParameterMap();
		
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		mapData.forEach((key, val) -> parameters.put(key, val[0]));
		
		mapData.forEach((key,val) -> System.out.println(key + " " + val));
		
		String paytmChecksum = "";
		if (mapData.containsKey("CHECKSUMHASH")) {
			paytmChecksum = mapData.get("CHECKSUMHASH")[0];
		}
		String result;
		boolean isValideChecksum = false;
		System.out.println("RESULT : " + parameters.toString());
		try {
			isValideChecksum = paytmPaymentService.validateCheckSum(parameters, paytmChecksum);
			if (isValideChecksum && parameters.containsKey("RESPCODE")) {
				if (parameters.get("RESPCODE").equals("01")) {
					result = "Payment Successful";
				} else {
					result = "Payment Failed";
				}
			} else {
				result = "Checksum mismatched";
			}
		} catch (Exception e) {
			result = e.toString();
		}
		
		parameters.remove("CHECKSUMHASH");
		return result;
	}

}
