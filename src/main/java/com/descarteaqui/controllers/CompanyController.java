package com.descarteaqui.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.descarteaqui.DAO.CompanyDAO;
import com.descarteaqui.DAO.CompanySpecifications;
import com.descarteaqui.domain.Company;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class CompanyController {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getHelloWorld(@RequestBody String searchData){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		Company company = null;
		State state = null;
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {
			node = mapper.readTree(searchData);
		} catch (IOException e) {
			model.put("erro", "Invalid data sent");
			return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
		} 
		
		try{
			company = mapper.convertValue(node.get("company"), Company.class);
			state = mapper.convertValue(node.get("state"), State.class);
		}catch(IllegalArgumentException e){
			model.put("erro", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
		}
		

		PageRequest request;
		
		try{
			request = new PageRequest(state.getStart() - 1, state.getLengthTable(), state.getSortValue(), state.getVarSort());
		}catch(IllegalArgumentException e) {
			model.put("erro", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
		}
		
		Specification<Company> specification = CompanySpecifications.filterMultiCollumn(company);
		List<Company> data;
		
		try{
			data = companyDAO.findAll(specification, request).getContent();
		}catch(PropertyReferenceException e) {
			model.put("erro", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(model, HttpStatus.BAD_REQUEST);
		}
		
		model.put("company", data);
		model.put("size", companyDAO.count());
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
	
}
