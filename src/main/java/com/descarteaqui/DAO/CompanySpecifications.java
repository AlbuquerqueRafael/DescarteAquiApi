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
	
	 public static Specification<Company> filterMultiCollumn(String name, String adress, String phone) {
	    return new Specification<Company>() {
	      @Override
	      public Predicate toPredicate(Root<Company> root,
	          CriteriaQuery<?> query, CriteriaBuilder builder) {
	    	  List<Predicate> predicates = new ArrayList<Predicate>();
	    	  
	    	  if(name != null){
	    		  predicates.add(builder.like(root.<String>get("name"), "%" + name + "%"));
	    		
	    	  }
	    	  
	    	  if(adress != null){
	    		  predicates.add(builder.like(root.<String>get("adress"), "%" + adress + "%"));
	    	  }
	    	  
	  	      if(phone != null){
	  	    	  predicates.add(builder.like(root.<String>get("phone"), "%" + phone + "%"));
	  	      }
	  	      
	  	      return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	  		  	   
	      }
	    };
	 }
		  
}
