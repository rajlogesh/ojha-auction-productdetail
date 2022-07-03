package com.auction.portal.service;

import com.auction.portal.model.request.ProductDetailsRequest;
import com.auction.portal.model.response.ProductDetailsResponse;

public interface ProductDetailService {

	public ProductDetailsResponse saveProductDetails(ProductDetailsRequest request);
	
	public ProductDetailsResponse editProductDetails(ProductDetailsRequest request);
	
	public ProductDetailsResponse deleteProductDetails(String productId);

	public ProductDetailsResponse geteProductByProductId(String productId);

	public ProductDetailsResponse getAlleProduct();

}
