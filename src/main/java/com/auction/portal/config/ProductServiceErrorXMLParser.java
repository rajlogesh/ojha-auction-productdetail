package com.auction.portal.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auction.portal.model.ProductServiceError;
import com.auction.portal.model.ProductServiceErrors;
import com.auction.portal.util.ProductDetailsUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductServiceErrorXMLParser {

	private static Map<String, ProductServiceError> map = new HashMap<>();
	
	@Autowired
	ProductDetailsUtil util;

	@PostConstruct
	public void init() {
		final String method = "ProductServiceErrorXMLParser::init";
		logger.debug("[{}] - Error xml parsing started", method);
		if (null == map || map.isEmpty()) {
			ProductServiceErrors errors = (ProductServiceErrors) util.convertFromXMLToObject();
			errorsMap(errors);
		}
		logger.debug("([{}] - Error xml parsing ended", method);
	}

	private void errorsMap(ProductServiceErrors errors) {
		if (null != errors && null != errors.getProductServiceErrorList()
				&& !errors.getProductServiceErrorList().isEmpty()) {
			for (ProductServiceError serviceError : errors.getProductServiceErrorList()) {
				map.put(serviceError.getErrorId(), serviceError);
			}
		}
	}

	public ProductServiceError readErorMessage(String key) {
		final String method = "ProductServiceErrorXMLParser::readyErrorMessage";
		logger.debug("[{}] - Reading the error details from map", method);
		return map.get(key);
	}
}
