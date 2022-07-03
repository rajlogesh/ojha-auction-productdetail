package com.auction.portal.controller;

import static com.auction.portal.constants.ProductDetailConstants.API_CONTEXT_ROOT;
import static com.auction.portal.constants.ProductDetailConstants.PRODUCT_DETAILS_CONTROLLER;
import static com.auction.portal.constants.ProductDetailConstants.DELETE_PRODUCT_DETAILS_URI;
import static com.auction.portal.constants.ProductDetailConstants.FETCHALL_PRODUCT_DETAILS_URI;
import static com.auction.portal.constants.ProductDetailConstants.FETCH_PRODUCT_DETAILS_URI;
import static com.auction.portal.constants.ProductDetailConstants.SAVE_PRODUCT_DETAILS_URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.portal.model.request.ProductDetailsRequest;
import com.auction.portal.model.response.ProductDetailsResponse;
import com.auction.portal.service.ProductDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = API_CONTEXT_ROOT, produces = { MediaType.APPLICATION_JSON_VALUE, }, consumes = {
		MediaType.APPLICATION_JSON_VALUE })
@Tag(name = PRODUCT_DETAILS_CONTROLLER)
public class ProductDetailsController {

	@Autowired
	ProductDetailService service;

	@Operation(summary = SAVE_PRODUCT_DETAILS_URI, description = "This API stores the product details in  DB")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Save product Data") })
	@PostMapping(SAVE_PRODUCT_DETAILS_URI)
	// @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@Parameters({ @Parameter(name = "HTTP_AUTH_TOKEN", description = "JWT token header", required = false) })
	public ResponseEntity<ProductDetailsResponse> saveProduct(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@Parameter(name = "ProductDetailsRequest", description = "Request Body", required = true) @RequestBody @Valid ProductDetailsRequest request) {
		logger.info("product Detail started");
		ProductDetailsResponse response = service.saveComapanyDetails(request);
		logger.info("product Detail ended");
		return ResponseEntity.ok(response);
	}

	@Operation(summary = SAVE_PRODUCT_DETAILS_URI, description = "This API stores the product details in  DB")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Save product Data") })
	@PutMapping(SAVE_PRODUCT_DETAILS_URI)
	// @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@Parameters({ @Parameter(name = "HTTP_AUTH_TOKEN", description = "JWT token header", required = false) })
	public ResponseEntity<ProductDetailsResponse> editProduct(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@Parameter(name = "ProductDetailsRequest", description = "Request Body", required = true) @RequestBody @Valid ProductDetailsRequest request) {
		logger.info("product Detail started");
		ProductDetailsResponse response = service.saveComapanyDetails(request);
		logger.info("product Detail ended");
		return ResponseEntity.ok(response);
	}

	@Operation(summary = DELETE_PRODUCT_DETAILS_URI, description = "This API deletes the product details from  DB")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete product Data") })
	@DeleteMapping(value = DELETE_PRODUCT_DETAILS_URI, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	// @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
	@Parameters({ @Parameter(name = "HTTP_AUTH_TOKEN", description = "JWT token header", required = false) })
	public ResponseEntity<ProductDetailsResponse> deleteProduct(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@Parameter(name = "productid", description = "productid", required = true) @PathVariable(name = "productid", required = true) String productid) {
		ProductDetailsResponse response = service.deleteComapanyDetails(productid);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = FETCH_PRODUCT_DETAILS_URI, description = "This API fetches the productid details for the requested company code")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get productid Data") })
	@GetMapping(value = FETCH_PRODUCT_DETAILS_URI, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	// @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or
	// hasRole('ROLE_ADMIN')")
	@Parameters({ @Parameter(name = "HTTP_AUTH_TOKEN", description = "JWT token header", required = false) })
	public ResponseEntity<ProductDetailsResponse> getProductByProductId(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken,
			@Parameter(name = "productid", description = "productid", required = true) @PathVariable(name = "productid", required = true) String productid) {
		ProductDetailsResponse response = service.getCompanyByCompanyCode(productid);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = FETCHALL_PRODUCT_DETAILS_URI, description = "This API fetches all the registered product details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get all product Data") })
	@GetMapping(value = FETCHALL_PRODUCT_DETAILS_URI, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	// @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or
	// hasRole('ROLE_ADMIN')")
	@Parameters({ @Parameter(name = "HTTP_AUTH_TOKEN", description = "JWT token header", required = false) })
	public ResponseEntity<ProductDetailsResponse> getAllProduct(
			@RequestHeader(required = false, value = "HTTP_AUTH_TOKEN") String jwtToken) {
		ProductDetailsResponse response = service.getAllCompany();
		return ResponseEntity.ok(response);
	}

}
