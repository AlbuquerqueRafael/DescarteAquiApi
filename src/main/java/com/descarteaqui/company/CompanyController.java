package com.descarteaqui.company;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.descarteaqui.state.State;
import com.descarteaqui.state.StateService;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private StateService tableStateService;
	
	@RequestMapping(value = "/company", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> getHelloWorld(@RequestBody String searchData) 
			throws IOException{
		Map<String, Object> model = new HashMap<String, Object>();

		JsonNode node = companyService.initNode(model, searchData);
		Company company = companyService.initCompany( node);
		State tableState = tableStateService.initTableState( node);
		PageRequest request = tableStateService.getPageRequest(tableState);
		
		model = companyService.getSearch(company, request, tableState);
		
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
	
	
	
}
