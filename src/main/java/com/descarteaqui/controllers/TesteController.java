package com.descarteaqui.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.descarteaqui.DAO.CompanyDAO;
import com.descarteaqui.DAO.CompanySpecifications;
import com.descarteaqui.domain.Company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
public class TesteController {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody List<Company> getHelloWorld(@RequestBody String str) throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node =  mapper.readTree(str);
		Company company = mapper.convertValue(node.get("company"), Company.class);
		State state = mapper.convertValue(node.get("state"), State.class);
		System.out.println("Company name: " + company.getName());
		System.out.println("State info: " + state.getLengthTable());
		
		PageRequest request = new PageRequest(0, 2, Sort.Direction.ASC, "name");
		Specification<Company> specification = CompanySpecifications.filterMultiCollumn(null, null, "32342423");
		
		List<Company> data = companyDAO.findAll(specification, request).getContent();
	
		return data;
	}
	
	
}
