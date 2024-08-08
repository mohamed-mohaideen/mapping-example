package com.example.mapping.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.mapping.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeSearchDao {
	
	private final EntityManager em;
	
	public List<Employee> findAllEmployeeSimple(String firstName, String lastName, String email) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		
//		select from employee
		Root<Employee> root = criteriaQuery.from(Employee.class);
		
//		firstname like %%
		Predicate firstNamePredicate = criteriaBuilder.like(root.get("first_name"), "%" + firstName + "%");

//		lastname like %%
		Predicate lastNamePredicate = criteriaBuilder.like(root.get("last_name"), "%" + lastName + "%");
		
//		email like %%
		Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");
		
//		firstname or lastname
		Predicate firstNameOrLastNamePredicate = criteriaBuilder.or(firstNamePredicate, lastNamePredicate);
		
//		firstname or lastname and email
		Predicate andEmailPredicate = criteriaBuilder.and(firstNameOrLastNamePredicate, emailPredicate);
		
//		where 
		criteriaQuery.where(andEmailPredicate);
		TypedQuery<Employee> query = em.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
	public List<Employee> findAllEmployee(String firstname, String lastname, String email) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		List<Predicate> predicates = new ArrayList<>();
		
		Root<Employee> root = criteriaQuery.from(Employee.class);
		
		if(firstname != null) {
			Predicate firstnamePredicate = criteriaBuilder.like(root.get("first_name"), "%" + firstname + "%");
			predicates.add(firstnamePredicate);
		}
		
		if(lastname != null) {
			Predicate lastnamePredicate = criteriaBuilder.like(root.get("last_name"), "%" + lastname + "%");
			predicates.add(lastnamePredicate);
		}
		
		if(email != null) {
			Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");
			predicates.add(emailPredicate);
		}
		
		Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
		Predicate orPredicate = criteriaBuilder.or(predicateArray);
		criteriaQuery.where(orPredicate);
		
		TypedQuery<Employee> query = em.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	

}
