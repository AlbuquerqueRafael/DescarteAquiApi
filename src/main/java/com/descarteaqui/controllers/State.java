package com.descarteaqui.controllers;

import org.springframework.data.domain.Sort;

public class State {
	private int lengthTable;
	private Sort.Direction sortValue;
	private int start;
	private String varSort;
	
	
	public State(){
		
	}
	
	public State(int lengthTable, Sort.Direction sortValue, int start, String varSort){
		this.lengthTable = lengthTable;
		this.sortValue = sortValue;
		this.start = start;
		this.varSort = varSort;
		
	}
	
	public int getLengthTable() {
		return lengthTable;
	}
	
	public void setLengthTable(int lengthTable) {
		this.lengthTable = lengthTable;
	}
	
	public Sort.Direction getSortValue() {
		return sortValue;
	}
	
	public void setSortValue(Sort.Direction sortValue) {
		this.sortValue = sortValue;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public String getVarSort() {
		return varSort;
	}
	
	public void setVarSort(String varSort) {
		this.varSort = varSort;
	}
}
