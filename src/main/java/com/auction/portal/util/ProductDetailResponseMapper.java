package com.auction.portal.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.stk.mrkt.ojhastkmrktcompanydetailservice.entity.CompanyDetailsEntity;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.request.CompanyDetailsRequest;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.CompanyDetail;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.StockDetails;
import com.stk.mrkt.ojhastockdetailservice.model.StockDetailEntity;
import com.stk.mrkt.ojhastockdetailservice.model.StockDetailResponse;

public class ProductDetailResponseMapper {

	public ProductDetailsEntity companyDeatilsRequestMapper(ProductDetailsRequest request, boolean updateFlag) {
		ProductDetailsEntity entity = new ProductDetailsEntity();
		entity.setCompanyCode(request.getCompanyCode());
		entity.setCompanyCEO(request.getCompanyCEO());
		entity.setCompanyName(request.getCompanyName());
		entity.setCompanyTurnover(request.getCompanyTurnover());
		entity.setCompanyWebsite(request.getCompanyWebsite());
		entity.setStockExchangeList(request.getStockExchangeList().stream().collect(Collectors.joining(",")));
		if(updateFlag) 
			entity.setUpdateDate(new Date());
		else
			entity.setCreationDate(new Date());
		return entity;
	}
	
	public CompanyDetail companyDetailsResponseMapper(ProductDetailsEntity entity, List<StockDetailEntity> stockResponse) {
		CompanyDetail response = new CompanyDetail();
		response.setCompanyCode(entity.getCompanyCode());
		response.setCompanyCEO(entity.getCompanyCEO());
		response.setCompanyName(entity.getCompanyName());
		response.setCompanyTurnover(entity.getCompanyTurnover());
		response.setCompanyWebsite(entity.getCompanyWebsite());
		response.setStockExchangeList(entity.getStockExchangeList());
		
		List<StockDetails> stockDetails = new ArrayList<>();
		for (StockDetailEntity stocks : stockResponse) {
			StockDetails stockDetail = new StockDetails();
			stockDetail.setStockPrice(stocks.getStockPrice());
			stockDetail.setStockUpdtTms(stocks.getStockUpdtTms());
			stockDetails.add(stockDetail);
		}
		
		response.setStockDetails(stockDetails);
		return response;
	}
	
	public List<CompanyDetail> getAllCompanyResponseMapper(List<ProductDetailsEntity> entities, StockDetailResponse stockResponse) {
		
		List<CompanyDetail> compantDetails = new ArrayList<>();
		for (ProductDetailsEntity companyDetailsEntity : entities) {
			List<StockDetailEntity>  stocks = stockResponse.getStockDetails().stream().filter(s->s.getCompanyCode()==companyDetailsEntity.getCompanyCode()).collect(Collectors.toList());
			CompanyDetail detail = companyDetailsResponseMapper(companyDetailsEntity, stocks);
			if(stocks != null && !stocks.isEmpty()) {
				detail.setMaxStockPrice(stocks.stream().mapToDouble(StockDetailEntity::getStockPrice).max().getAsDouble());
				detail.setMinStockPrice(stocks.stream().mapToDouble(StockDetailEntity::getStockPrice).min().getAsDouble());
				detail.setAvgStockPrice(stocks.stream().collect(Collectors.averagingDouble(StockDetailEntity::getStockPrice)));
			}
			compantDetails.add(detail);
		}
		return compantDetails;
	}
}
