package com.auction.portal.constants;

import java.util.Arrays;
import java.util.List;

public class ProductDetailConstants {
	
	public static final String API_CONTEXT_ROOT = "/e-auction/api/v1";
	public static final String SAVE_PRODUCT_DETAILS_URI = "/product/save";
	public static final String FETCHALL_PRODUCT_DETAILS_URI = "/product/getall";
	public static final String FETCH_PRODUCT_DETAILS_URI = "/product/get/{productid}";
	public static final String DELETE_PRODUCT_DETAILS_URI = "/product/delete/{productid}";
	public static final String PRODUCT_DETAILS_CONTROLLER = "PRODUCT DEATILS CONTROLLER";
	
	public static final String PRODUCT_SERVICE_ERROR_XML_FILE_PATH = "/productservice-errors.xml";
	
	public static final String REQUEST_ID = "requestId";
	public static final String STRING_Y = "Y";
	public static final String STRING_N = "N";
	
	public static final String INPUT_FIELD_REQUIRED = "field.required";
	public static final String INPUT_FIELD_INVALID = "field.invalid";
	public static final String INPUT_FIELD_INVALID_NOTALLOWED = "field.invalid.notallowed";
	public static final String INPUT_FIELD_LENGTH_INVALID = "field.invalid.length";
	public static final String DOWNSTREAM_ERROR = "downstream.error";
	public static final String DOWNSTREAM_NULL_RESPONSE = "downstream.nullresponse";
	public static final String COMPANY_TURNOVER_ELIGIBILITY = "company.turnover.eligibility";
	public static final String COMPANY_STOCKLIST_ELIGIBILITY = "company.stocklist.eligibility";
	
	public static final String INVALID_COMPANY_CODE = "invalid.companycode";
	public static final String ALPHA_REGEX = "^[a-zA-Z]+$";
	public static final String ALPHA_NUMERIC_REGEX = "^[a-zA-Z0-9]+$";
	public static final String NUMERIC_REGEX = "^[+-]?[0-9]*$";
	public static final String NUMERIC_DECIMEL_REGEX = "^[+-]?[0-9]*\\.?[0-9]*$";
	public static final String WEBSITE_REGEX = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$";
	public static final String XSS_SQL_INJECTION_REGEX = "(((\\\\%3C)|<)[^\\\\n]+((\\\\%3E)|>)|((\\\\%3C)|<)|((\\\\%3E)|>)|((\\\\%3B)|;)|((\\\\%60)|`)|((\\\\%22\\\\%2D|\\\"\\\\-))|((\\\\%22\\\\%2D|'\\\\-)))|(((\\\\\\\\%3D)|(=))[^\\\\\\\\n]*((\\\\\\\\%27)|(\\\\\\\\')|(\\\\\\\\-\\\\\\\\-)|(\\\\\\\\%3B)|(;)))";
	
	public static final List<String> FINANCE_MARKET_LIST = Arrays.asList("NSE","BSE");
}
