package com.descarteaqui.company;

import java.io.IOException;
import java.util.ArrayList;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CompanyService {
	@Autowired
	private CompanyDAO companyDAO;
	
	public JsonNode initNode(Map<String, Object> model, String searchData) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		node = mapper.readTree(searchData);
		
		return node;
	}
	
	public Company initCompany(JsonNode node){
		ObjectMapper mapper = new ObjectMapper();
		Company company = new Company();

		try{
			company = mapper.convertValue(node.get("company"), Company.class);
		}catch(IllegalArgumentException e){
			throw new InvalidCompanyAttributeException(e.getMessage());
		}
		
		return company;
	}
	
	
	public Map<String, Object> getSearch(Company company, PageRequest request, State tableState){
		List<Company> data = new ArrayList<Company>();
		long size = 0;
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		try{
			//Throws null pointer if company is null;
			Specification<Company> specification = CompanySpecifications.filterMultiCollumn(company);
			//Throws property exception if varSort value do not match with any attribute from object
			Page<Company> page = companyDAO.findAll(specification, request);
			data = page.getContent();
			size = page.getTotalPages() * tableState.getLengthTable();
		}catch(NullPointerException e) {
			throw new CompanyJsonNotFoundException("Company json was not sent");
		}catch(PropertyReferenceException e) {
			throw new InvalidSortableVarPropertyException(e.getMessage());
		}
		
		model.put("company", data);
		model.put("size", size);
		
		return model;
	
	}
	
}
