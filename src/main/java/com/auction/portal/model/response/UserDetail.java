package com.auction.portal.model.response;

import lombok.Data;

@Data
public class UserDetail {

	private Long userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String country;
	private Integer pin;
	private Integer phone;
	private String email;

}
