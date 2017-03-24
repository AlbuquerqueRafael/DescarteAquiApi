package com.descarteaqui.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import com.descarteaqui.company.exceptions.CompanyJsonNotFoundException;
import com.descarteaqui.company.exceptions.InvalidCompanyAttributeException;
import com.descarteaqui.state.State;
import com.descarteaqui.state.exceptions.InvalidSortableVarPropertyException;
import com.descarteaqui.user.exceptions.InvalidUserInfoException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public JsonNode initNode(Map<String, Object> model, String searchData) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		node = mapper.readTree(searchData);
		
		return node;
	}
	
	public AppUser initCompany(JsonNode node){
		ObjectMapper mapper = new ObjectMapper();
		AppUser user = new AppUser();

		try{
			user = mapper.convertValue(node.get("user"), AppUser.class);
		}catch(IllegalArgumentException e){
			throw new InvalidCompanyAttributeException(e.getMessage());
		}
		
		return user;
	}
	
	
	public Map<String, Object> getSearch(AppUser user, PageRequest request, State tableState){
		List<AppUser> data = new ArrayList<AppUser>();
		long size = 0;
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		try{
			//Throws null pointer if company is null;
			Specification<AppUser> specification = UserSpecifications.filterMultiCollumn(user);
			//Throws property exception if varSort value do not match with any attribute from object
			Page<AppUser> page = userDAO.findAll(specification, request);
			data = page.getContent();
			size = page.getTotalPages() * tableState.getLengthTable();
		}catch(NullPointerException e) {
			throw new CompanyJsonNotFoundException("Company json was not sent");
		}catch(PropertyReferenceException e) {
			throw new InvalidSortableVarPropertyException(e.getMessage());
		}
		
		model.put("user", data);
		model.put("size", size);
		
		return model;
	
	}
	
	public Map<String, Object> checkAuth(AppUser appUser, String password, String username){
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		String token = null;
		
		if (appUser != null && appUser.getPassword().equals(password)) {
			//5 days expiration time
			Calendar calendar = Calendar.getInstance(); 
			calendar.add(Calendar.DAY_OF_MONTH, 5);
			
			token = Jwts.builder().setSubject(username).claim("roles", appUser.getRoles())
					.setIssuedAt(new Date())
					.setExpiration(calendar.getTime())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("user", appUser);
		} else {
			throw new InvalidUserInfoException("Invalid username or login");
		}
		
		return tokenMap;
	}
}
