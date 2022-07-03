package com.auction.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.auction.portal.client.BidMgmtServiceClient;
import com.auction.portal.client.impl.BidMgmtServiceClientImpl;
import com.auction.portal.util.ProductDetailResponseMapper;
import com.auction.portal.util.ProductDetailsUtil;

@Configuration
public class ProductDetailConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ProductDetailsUtil companyDetailsUtil() {
		return new ProductDetailsUtil();
	}
	
	@Bean
	public ProductDetailResponseMapper companyDetailResponseMapper() {
		return new ProductDetailResponseMapper();
	}
	
	@Bean
	public BidMgmtServiceClient stockServiceClient() {
		return new BidMgmtServiceClientImpl();
	}
//	
//	@Bean
//	public UserDetailServiceImpl userDetailServiceImpl() {
//		return new UserDetailServiceImpl();
//	}
	
	
}
