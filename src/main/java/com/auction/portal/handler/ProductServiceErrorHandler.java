package com.auction.portal.handler;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.config.CompanyServiceErrorXMLParser;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.constants.CompanyDetailConstants;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.exception.InputValidationException;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.CompanyServiceError;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.ErrorResponse;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.util.CompanyDetailsUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ProductServiceErrorHandler {

	@Autowired
	ProductDetailsUtil util;
	
	@Autowired
	ProductServiceErrorXMLParser parser;
	
	@ExceptionHandler(value = InputValidationException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleInputValidationException(InputValidationException e) {
		logger.error("handleInputValidationException, InputValidationException exception: ",e);
		ProductServiceError error = parser.readErorMessage(e.getCode());
		if(null != error) {
			String errorMessage = StringUtils.isEmpty(e.getMessage()) ? error.getErrorMessage():e.getMessage() +error.getErrorMessage();
			return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, MDC.get(ProductDetailConstants.REQUEST_ID), Integer.parseInt(error.getErrorCode()), errorMessage);
		}
		return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, MDC.get(ProductDetailConstants.REQUEST_ID),123, e.getMessage());
	}
	
//	@ExceptionHandler(value = RuntimeException.class)
//	@ResponseBody
//	public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
//		logger.error("handleRunTimeException, Runtime exception: ",e);
//		CompanyServiceError error = parser.readErorMessage(e.getMessage());
//		if(null != error) {
//			String errorMessage = StringUtils.isEmpty(e.getMessage()) ? error.getErrorMessage():e.getMessage() +error.getErrorMessage();
//			return buildErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, MDC.get(CompanyDetailConstants.REQUEST_ID), Integer.parseInt(error.getErrorCode()), errorMessage);
//		}
//		return buildErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, MDC.get(CompanyDetailConstants.REQUEST_ID),123, e.getMessage());
//	}
	
	private ResponseEntity<ErrorResponse> buildErrorResponseEntity(HttpStatus httpstatus, String requestId,Integer errorCode,String errorMessage) {
		String responseId = util.getResponseId();
		logger.error("Exception in Company Service : ", errorMessage);
		ErrorResponse response = new ErrorResponse();
		response.setRequestId(requestId);
		response.setResponseId(responseId);
		response.setErrorCode(errorCode);
		response.setErrorMessage(errorMessage);
		ResponseEntity<ErrorResponse> resp = new ResponseEntity<>(response, httpstatus);
		MDC.clear();
		return resp;
	}
}
