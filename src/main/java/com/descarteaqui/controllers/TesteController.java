package com.descarteaqui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.descarteaqui.DAO.CompanyDAO;
import com.descarteaqui.DAO.CompanySpecifications;
import com.descarteaqui.domain.Company;

@RestController
public class TesteController {
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<Company> getHelloWorld(){
		PageRequest request = new PageRequest(0, 2, Sort.Direction.ASC, "name");
		Specification<Company> specification = CompanySpecifications.filterMultiCollumn(null, null, "32342423");
		
		List<Company> data = companyDAO.findAll(specification, request).getContent();
	
		return data;
	}
	
	
}
