package com.example.mapping.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mission {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Integer duration;
	
	@ManyToMany(mappedBy="missions")
	private List<Employee> employees;

}
