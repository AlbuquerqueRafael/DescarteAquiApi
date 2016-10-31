package com.descarteaqui.controllers;

import com.descarteaqui.domain.Company;

public class PersonContext {
   private State userContext;
   private Company person;
   
   public PersonContext(){
	   
   }
   
   public PersonContext(State userContext, Company person){
	   this.userContext = userContext;
	   this.person = person;
   }

public State getUserContext() {
	return userContext;
}

public void setUserContext(State userContext) {
	this.userContext = userContext;
}

public Company getPerson() {
	return person;
}

public void setPerson(Company person) {
	this.person = person;
}
   
   
}
