package com.auction.portal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productservice-errors")
public class ProductServiceErrors {

	private List<ProductServiceError> productServiceErrorList;

	public List<ProductServiceError> getProductServiceErrorList() {
		return productServiceErrorList;
	}

	@XmlElement(name = "productservice-error")
	public void setProductServiceErrorList(List<ProductServiceError> productServiceErrorList) {
		this.productServiceErrorList = productServiceErrorList;
	}

	@Override
	public String toString() {
		return "ProductServiceErrors [productServiceErrorList=" + productServiceErrorList + "]";
	}

}
