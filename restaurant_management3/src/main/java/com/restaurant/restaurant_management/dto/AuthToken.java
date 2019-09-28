package com.restaurant.restaurant_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
	
	private String token;

	public AuthToken(String token) {
		super();
		this.token = token;
	}

	public AuthToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
