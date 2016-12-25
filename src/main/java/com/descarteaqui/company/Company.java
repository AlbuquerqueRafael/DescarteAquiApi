package com.descarteaqui.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Company")
public class Company implements Serializable {
	
	private static final long serialVersionUID = -1785131699037567362L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "adress")
	private String adress;
	@Column(name = "phone")
	private String phone;
	
	public Company(){
		
	}
	
	public Company(String name, String adress, String phone){
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public Long getId(){
		return id;
	}

	

}
