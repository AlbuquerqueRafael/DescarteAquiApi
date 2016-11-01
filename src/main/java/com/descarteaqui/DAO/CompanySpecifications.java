package com.descarteaqui.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.descarteaqui.domain.Company;

public class CompanySpecifications {
	
	 public static Specification<Company> filterMultiCollumn(Company company) {
	    return new Specification<Company>() {
	      @Override
	      public Predicate toPredicate(Root<Company> root,
	          CriteriaQuery<?> query, CriteriaBuilder builder) {
	    	  List<Predicate> predicates = new ArrayList<Predicate>();
	    	  
	    	  if(company.getName() != null){
	    		  predicates.add(builder.like(builder.lower(root.<String>get("name")), "%" + company.getName().toLowerCase() + "%"));
	    		
	    	  }
	    	  
	    	  if(company.getAdress() != null){
	    		  predicates.add(builder.like(builder.lower(root.<String>get("adress")), "%" + company.getAdress().toLowerCase() + "%"));
	    	  }
	    	  
	  	      if(company.getPhone() != null){
	  	    	  predicates.add(builder.like(builder.lower(root.<String>get("phone")), "%" + company.getPhone().toLowerCase() + "%"));
	  	      }
	  	      
	  	      return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	  		  	   
	      }
	    };
	 }
		  
}
