package com.restaurant.restaurant_management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="address")
@Entity
public class Address {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long address_id;
	@Column
	private String street;
	@Column
	private String city;
	@Column
	private String pincode;

}
