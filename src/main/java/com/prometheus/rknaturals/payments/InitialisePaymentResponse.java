package com.prometheus.rknaturals.payments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InitialisePaymentResponse {

	private Head head;
	private Body body;

	public InitialisePaymentResponse() {
		
	}
	
	public InitialisePaymentResponse(Head head, Body body) {
		super();
		this.head = head;
		this.body = body;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Head {
	private String responseTimestamp;
	private String version;
	private String clientId;
	private String signature;
	private String requestId;

	public Head() {

	}

	public Head(String responseTimestamp, String version, String clientId, String signature, String requestId) {
		super();
		this.responseTimestamp = responseTimestamp;
		this.version = version;
		this.clientId = clientId;
		this.signature = signature;
		this.requestId = requestId;
	}

	public String getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class ResultInfo {
	private String resultStatus;
	private String resultCode;
	private String resultMsg;

	public ResultInfo() {
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Body {
	private ResultInfo resultInfo;
	private String txnToken;

	public Body() {
	}

	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}

	public String getTxnToken() {
		return txnToken;
	}

	public void setTxnToken(String txnToken) {
		this.txnToken = txnToken;
	}

}
