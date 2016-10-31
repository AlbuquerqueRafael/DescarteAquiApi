package com.descarteaqui.controllers;

public class State {
	private int lengthTable;
	private int sortValue;
	private int start;
	private String varSort;
	
	
	public State(){
		
	}
	
	public State(int lengthTable, int sortValue, int start, String varSort){
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
	
	public int getSortValue() {
		return sortValue;
	}
	
	public void setSortValue(int sortValue) {
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
