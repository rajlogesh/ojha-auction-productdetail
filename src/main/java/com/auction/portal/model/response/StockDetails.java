package com.auction.portal.model.response;

import java.util.Date;

import lombok.Data;

@Data
public class StockDetails {
	private Double stockPrice;
	private Date stockUpdtTms;
}
