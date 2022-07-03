package com.auction.portal.model.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.auction.portal.constants.ProductDetailConstants;
import com.auction.portal.handler.InputValidateData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ProductDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long companyCode;

	@InputValidateData(required = ProductDetailConstants.STRING_Y, regex = ProductDetailConstants.ALPHA_NUMERIC_REGEX, max = 255)
	private String companyName;

	@InputValidateData(required = ProductDetailConstants.STRING_Y, regex = ProductDetailConstants.ALPHA_REGEX, max = 100)
	private String companyCEO;

	@InputValidateData(required = ProductDetailConstants.STRING_Y, regex = ProductDetailConstants.NUMERIC_DECIMEL_REGEX)
	private BigDecimal companyTurnover;

	@InputValidateData(required = ProductDetailConstants.STRING_Y, regex = ProductDetailConstants.WEBSITE_REGEX)
	private String companyWebsite;

	private List<String> stockExchangeList;

}
