package com.auction.portal.serviceimpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.portal.client.BidMgmtServiceClient;
import com.auction.portal.constants.ProductDetailConstants;
import com.auction.portal.dao.ProductDetailsRepository;
import com.auction.portal.entity.ProductDetailsEntity;
import com.auction.portal.exception.InputValidationException;
import com.auction.portal.model.request.ProductDetailsRequest;
import com.auction.portal.model.response.ProductDetailsResponse;
import com.auction.portal.service.ProductDetailService;
import com.auction.portal.util.ProductDetailResponseMapper;
import com.auction.portal.util.ProductDetailsUtil;
import com.stk.mrkt.ojhastkmrktcompanydetailservice.model.response.CompanyDetail;
import com.stk.mrkt.ojhastockdetailservice.model.StockDetailEntity;
import com.stk.mrkt.ojhastockdetailservice.model.StockDetailResponse;

@Service
public class ProductDetailsServiceImpl implements ProductDetailService {

	@Autowired
	ProductDetailsRepository repository;

	@Autowired
	BidMgmtServiceClient client;

	@Autowired
	ProductDetailResponseMapper mapper;

	@Autowired
	ProductDetailsUtil util;

	@Override
	public ProductDetailsResponse saveProductDetails(ProductDetailsRequest request) {
		boolean updateFlag = false;

		// Validate few parameters in the request
		validateCompanyTurnOver(request.getCompanyTurnover());
		validateStockExchangeList(request.getStockExchangeList());

		// mapper to map request to entity object
		ProductDetailsEntity entity = mapper.companyDeatilsRequestMapper(request, updateFlag);

		// Call to DAO layer
		ProductDetailsEntity result = repository.save(entity);

		// Form the response
		ProductDetailsResponse response = new ProductDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data saved successfully");

		return response;
	}

	@Override
	public ProductDetailsResponse editProductDetails(ProductDetailsRequest request) {
		boolean updateFlag = true;

		// Validate few parameters in the request
		validateCompanyTurnOver(request.getCompanyTurnover());
		validateStockExchangeList(request.getStockExchangeList());

		// mapper to map request to entity object
		ProductDetailsEntity entity = mapper.companyDeatilsRequestMapper(request, updateFlag);

		// Call to DAO layer
		ProductDetailsEntity result = repository.save(entity);

		// Form the response
		ProductDetailsResponse response = new ProductDetailsResponse();
		response.setCompanyCode(result.getCompanyCode().toString());
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data saved successfully");

		return response;
	}
	
	private void validateStockExchangeList(List<String> stockExchangeList) {
		if (stockExchangeList == null || stockExchangeList.isEmpty()) {
			throw new InputValidationException(ProductDetailConstants.INPUT_FIELD_REQUIRED, "stockExchangeList");
		}

		for (String financeMarket : stockExchangeList) {
			if (!ProductDetailConstants.FINANCE_MARKET_LIST.contains(financeMarket)) {
				throw new InputValidationException(ProductDetailConstants.COMPANY_STOCKLIST_ELIGIBILITY);
			}
		}

	}

	private void validateCompanyTurnOver(BigDecimal companyTurnOver) {
		if (companyTurnOver == null || companyTurnOver.compareTo(BigDecimal.ZERO) == 0) {
			throw new InputValidationException(ProductDetailConstants.INPUT_FIELD_REQUIRED, "companyTurnOver");
		}

		if (companyTurnOver.compareTo(new BigDecimal(100000000)) < 0) {
			throw new InputValidationException(ProductDetailConstants.COMPANY_TURNOVER_ELIGIBILITY);
		}

	}

	@Override
	public ProductDetailsResponse deleteProductDetails(String productId) {
		Long companyId = Long.valueOf(companyCode);
		
		boolean companyExists = repository.existsById(companyId);
		
		if(!companyExists)
			throw new InputValidationException(ProductDetailConstants.INVALID_COMPANY_CODE);
		
		client.deleteStocksOfComapny(companyCode);
		repository.deleteById(companyId);
		ProductDetailsResponse response = new ProductDetailsResponse();
		response.setResponseID(util.getResponseId());
		response.setResponseMsg("data deleted successfully");
		return response;
	}

	@Override
	public ProductDetailsResponse getProductByProductId(String productId) {
		Long companyId = Long.valueOf(companyCde);
		
		ProductDetailsEntity result = repository.findById(companyId).orElseThrow(() -> new InputValidationException(ProductDetailConstants.INVALID_COMPANY_CODE));
		StockDetailResponse stockResult = client.getStockByCompanyCode(companyCde);
		
		ProductDetailsResponse response = new ProductDetailsResponse();
		CompanyDetail companyDetail = mapper.companyDetailsResponseMapper(result, stockResult.getStockDetails());
		response.setResponseID(util.getResponseId());
		response.setCompanyDetail(companyDetail);
		response.setResponseMsg("fetched data successfully");
		return response;
	}

	@Override
	public ProductDetailsResponse getAllProduct() {
		List<ProductDetailsEntity> result = repository.findAll();
		StockDetailResponse stockResult = client.getAllCompanyStocks();
		
		ProductDetailsResponse response = new ProductDetailsResponse();
		List<CompanyDetail> companyDetails = mapper.getAllCompanyResponseMapper(result, stockResult);
		response.setResponseID(util.getResponseId());
		response.setCompanyDetails(companyDetails);
		response.setResponseMsg("fetched data successfully");
		return response;
	}

}
