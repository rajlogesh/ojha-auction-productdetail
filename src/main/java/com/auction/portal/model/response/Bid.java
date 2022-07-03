package com.auction.portal.model.response;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Bid {

	@Id
	private String id;
	private String productId;
	private String userId;
	private Double bidAmount;
	private UserDetail user;
	private Date bidUpdtTms;

}