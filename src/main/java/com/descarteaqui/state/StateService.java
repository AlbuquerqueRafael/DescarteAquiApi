package com.descarteaqui.state;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.descarteaqui.state.exceptions.InvalidStateAttributeException;
import com.descarteaqui.state.exceptions.InvalidStateAttributeValuesException;
import com.descarteaqui.state.exceptions.StateJsonNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StateService {
	
	public State initTableState(JsonNode node){
		ObjectMapper mapper = new ObjectMapper();
		State tableState = new State();
		
		try{
			tableState = mapper.convertValue(node.get("state"), State.class);
		}catch(IllegalArgumentException e){
			throw new InvalidStateAttributeException(e.getMessage());
		}
		
		return tableState;
	}
	
	public PageRequest getPageRequest(State tableState){
		PageRequest request = null;
		
		try{
			request = new PageRequest(tableState.getStart() - 1, tableState.getLengthTable(), tableState.getSortValue(), tableState.getVarSort());
		}catch(NullPointerException e) {
			throw new StateJsonNotFoundException("State json was not sent");
		}catch(IllegalArgumentException e) {
			throw new InvalidStateAttributeValuesException(e.getMessage());
		}
		
		return request;
	}
}
