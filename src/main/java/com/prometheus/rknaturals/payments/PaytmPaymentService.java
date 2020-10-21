package com.prometheus.rknaturals.payments;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paytm.pg.merchant.PaytmChecksum;
import com.prometheus.rknaturals.util.Encryptor;

@Service
public class PaytmPaymentService {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private Environment environment;
	
	public void initiateTransaction(String customerID,String orderID,String amount) throws Exception {
		
		String mid = Encryptor.decrypt(environment.getProperty("mid"));
		String mkey = Encryptor.decrypt(environment.getProperty("mkey"));
		
		String callBackUrl = "";
		
		String url = "https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid="+mid+"&orderId="+orderID;
		
		JSONObject paytmParams = new JSONObject();

		JSONObject body = new JSONObject();
		body.put("requestType", "Payment");
		body.put("mid", mid);
		body.put("websiteName", "WEBSTAGING");
		body.put("orderId", orderID);
		body.put("callbackUrl", callBackUrl);

		JSONObject txnAmount = new JSONObject();
		txnAmount.put("value", amount);
		txnAmount.put("currency", "INR");

		JSONObject userInfo = new JSONObject();
		userInfo.put("custId", customerID);
		body.put("txnAmount", txnAmount);
		body.put("userInfo", userInfo);

		String checksum = PaytmChecksum.generateSignature(body.toString(), mkey);

		JSONObject head = new JSONObject();
		head.put("signature", checksum);

		paytmParams.put("body", body);
		paytmParams.put("head", head);

		String post_data = paytmParams.toString();

		// URL("https://securegw.paytm.in/theia/api/v1/initiateTransaction?mid=YOUR_MID_HERE&orderId=ORDERID_98765");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = 
			      new HttpEntity<String>(post_data.toString(), headers);
			    
		String output = 
			      restTemplate.postForObject(url, request, String.class);
		
		InitialisePaymentResponse  response= objectMapper.readValue(output, InitialisePaymentResponse.class);
		
		if(response.getBody().getResultInfo().getResultStatus().trim().equals("S")) {
			System.out.println("successfull");
		}
		else {
			System.out.println(response.getBody().getResultInfo().getResultCode());
			System.out.println("failure" + response.getBody().getResultInfo().getResultMsg());
		}
	}
}
