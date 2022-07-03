package com.auction.portal.client.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.auction.portal.client.BidMgmtServiceClient;
import com.auction.portal.constants.ProductDetailConstants;
import com.auction.portal.exception.ClientServiceException;
import com.auction.portal.model.response.Bid;
import com.auction.portal.model.response.ErrorResponse;
import com.auction.portal.util.ProductDetailsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BidMgmtServiceClientImpl implements BidMgmtServiceClient {

	@Value("${bidmgmtservice.rest.baseuri}")
	private String baseuri;

	@Value("${bidmgmtservice.rest.resourceuri.deleteproductbids}")
	private String deleteproductbids;

	@Value("${bidmgmtservice.rest.resourceuri.getallproductbids}")
	private String getallproductbids;

	@Value("${bidmgmtservice.rest.resourceuri.getproductbids}")
	private String getproductbids;

	@Autowired
	ProductDetailsUtil utils;

	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate;

	@Override
	public Bid deleteBidsOfProduct(String productId) {
		final String method = "BidMgmtServiceClientImpl::deleteBidsOfProduct";
		long start = System.currentTimeMillis();
		Bid response = null;
		logger.info("[{}] - Company Code : {}", method, productId);

		try {

			// Generating the REST API URL for a Stock Service - Delete stocks of a company
			String bidServiceUrl = new StringBuilder(baseuri).append(deleteproductbids).toString();

			HttpEntity<String> entity = new HttpEntity<>(utils.getHttpHeaders());
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("productid", productId);

			String url = UriComponentsBuilder.fromUriString(bidServiceUrl).buildAndExpand(urlParams).toUriString();

			logger.info("[{}] - About to invoke stock service - {}", method, url);
			ResponseEntity<Bid> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Bid.class);

			response = result.getBody();

			// Validate Response
			if (response == null) {
				logger.error("[{}] - Response is null from Downstream", method);
				throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_NULL_RESPONSE);
			}

			logger.info("[{}] - Received Response in - {}", method, start - System.currentTimeMillis());
			logger.debug("[{}] - Stock Service Response - {}", method, response);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throwServiceInvocationException(e);
		} catch (Exception e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_ERROR);
		}

		return response;

	}

	@Override
	public Bid getAllProductBids() {
		final String method = "BidMgmtServiceClientImpl::getAllProductBids";
		long start = System.currentTimeMillis();
		Bid response = null;

		try {

			// Generating the REST API URL for a Stock Service - Delete stocks of a company
			String bidServiceUrl = new StringBuilder(baseuri).append(getallproductbids).toString();

			HttpEntity<String> entity = new HttpEntity<>(utils.getHttpHeaders());

			String url = UriComponentsBuilder.fromUriString(bidServiceUrl).toUriString();

			logger.info("[{}] - About to invoke stock service - {}", method, url);
			ResponseEntity<Bid> result = restTemplate.exchange(url, HttpMethod.GET, entity, Bid.class);

			response = result.getBody();

			// Validate Response
			if (response == null) {
				logger.error("[{}] - Response is null from Downstream", method);
				throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_NULL_RESPONSE);
			}

			logger.info("[{}] - Received Response in - {}", method, start - System.currentTimeMillis());
			logger.debug("[{}] - Stock Service Response - {}", method, response);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throwServiceInvocationException(e);
		} catch (Exception e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_ERROR);
		}

		return response;

	}

	@Override
	public Bid getBidsByProductId(String productId) {
		final String method = "BidMgmtServiceClientImpl::getBidsByProductId";
		long start = System.currentTimeMillis();
		Bid response = null;
		logger.info("[{}] - Company Code : {}", method, productId);

		try {

			// Generating the REST API URL for a Stock Service - Delete stocks of a company
			String bidServiceUrl = new StringBuilder(baseuri).append(getproductbids).toString();

			HttpEntity<String> entity = new HttpEntity<>(utils.getHttpHeaders());
			Map<String, String> urlParams = new HashMap<>();
			urlParams.put("productid", productId);

			String url = UriComponentsBuilder.fromUriString(bidServiceUrl).buildAndExpand(urlParams).toUriString();

			logger.info("[{}] - About to invoke stock service - {}", method, url);
			ResponseEntity<Bid> result = restTemplate.exchange(url, HttpMethod.GET, entity, Bid.class);

			response = result.getBody();

			// Validate Response
			if (response == null) {
				logger.error("[{}] - Response is null from Downstream", method);
				throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_NULL_RESPONSE);
			}

			logger.info("[{}] - Received Response in - {}", method, start - System.currentTimeMillis());
			logger.debug("[{}] - Stock Service Response - {}", method, response);

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throwServiceInvocationException(e);
		} catch (Exception e) {
			logger.error("[{}] - Exception Occured - {}", method, e);
			throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_ERROR);
		}

		return response;

	}

	private void throwServiceInvocationException(HttpStatusCodeException e) {
		final String method = "StockServiceClientImpl::StockServiceClientImpl";

		HttpStatus status = e.getStatusCode();

		if (status == HttpStatus.NOT_FOUND) {
			logger.error("[{}] - failed due to wrong url", method);
			throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_ERROR);
		}

		ObjectMapper mapper = new ObjectMapper();
		ErrorResponse error = null;

		try {
			error = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
		} catch (Exception e1) {
			logger.error("[{}] - Exception Occured - {}", method, e1);
			throw new ClientServiceException(ProductDetailConstants.DOWNSTREAM_ERROR);
		}

		throw new ClientServiceException(error.getErrorCode().toString(), error.getErrorMessage(), status);
	}
}
