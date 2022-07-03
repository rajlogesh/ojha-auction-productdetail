package com.auction.portal.model.response;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductDetail {

	private Long productId;
	private String productName;
	private String category;
	private String shortDescription;
	private String detailedDescription;
	private Double startingPrice;
	private Double finalPrice;
	private Date bidEndDate;
	private Long owner;
	private UserDetail user;
	private List<Bid> bids;

}
