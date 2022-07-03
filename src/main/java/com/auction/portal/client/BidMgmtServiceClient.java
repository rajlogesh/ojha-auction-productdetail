package com.auction.portal.client;

import com.auction.portal.model.response.Bid;

public interface BidMgmtServiceClient {
	public Bid deleteBidsOfProduct(String productId);

	public Bid getAllProductBids();

	public Bid getBidsByProductId(String productId);

}
