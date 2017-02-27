package com.descarteaqui.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
	
	public static Specification<AppUser> filterMultiCollumn(AppUser user) {
	    return new Specification<AppUser>() {
	      @Override
	      public Predicate toPredicate(Root<AppUser> root,
	          CriteriaQuery<?> query, CriteriaBuilder builder) {
	    	  List<Predicate> predicates = new ArrayList<Predicate>();
	    	  
	    	  if(user.getName() != null){
	    		  predicates.add(builder.like(builder.lower(root.<String>get("name")), "%" + user.getName().toLowerCase() + "%"));
	    		
	    	  }
	    	  
	    	  if(user.getUsername() != null){
	    		  predicates.add(builder.like(builder.lower(root.<String>get("username")), "%" + user.getUsername().toLowerCase() + "%"));
	    	  }
	    	  
	    	
	  	      if(user.getRoles().size() != 0 && !user.getRoles().get(0).equals("")){
	  	    	  String role = user.getRoles().get(0).toUpperCase();
	  	    	  predicates.add(builder.like(root.join("roles"), "%" + role + "%"));
	  	      }
	  	      
	  	      return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	  		  	   
	      }
	    };
	 }
}
