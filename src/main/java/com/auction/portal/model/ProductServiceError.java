package com.auction.portal.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productservice-error")
public class ProductServiceError {

	private String errorId;
	private String errorCode;
	private String errorMessage;

	public String getErrorId() {
		return errorId;
	}

	@XmlAttribute(name = "errorId")
	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	@XmlAttribute(name = "code")
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@XmlAttribute(name = "message")
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ProductServiceError [errorId=" + errorId + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ "]";
	}

}
