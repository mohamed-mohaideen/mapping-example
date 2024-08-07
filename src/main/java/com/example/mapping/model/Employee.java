package com.example.mapping.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String firstName;
	private String lastName;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@ManyToMany
	@JoinTable(
			name = "employee_mission",
			joinColumns = { @JoinColumn(name="employee_id")},
			inverseJoinColumns = {@JoinColumn(name="mission_id")} )
	private List<Mission> missions;

}
