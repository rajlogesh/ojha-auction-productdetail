package com.auction.portal.model.response;

import java.util.List;

import lombok.Data;

@Data
public class ProductDetailsResponse extends SuccessResponse {

	private String productId;
	private ProductDetail productDetail;
	private List<ProductDetail> productDetails;

}
