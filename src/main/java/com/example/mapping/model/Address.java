package com.example.mapping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String streetName;
	private String city;
	private String pincode;
	
//	@OneToOne
//	@JoinColumn(name = "employee_id")
//	private Employee employee;

}
