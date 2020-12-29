package com.prometheus.rknaturals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	String internal(Exception ex) {
		return ex.getMessage();
	}
	
//	@ResponseBody
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	String exception(Exception ex) {
//		return ex.getMessage();
//	}
	
}
