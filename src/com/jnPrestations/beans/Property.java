package com.jnPrestations.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
@Entity
@Table(name="Properties")
public class Property {
	
	@Id @GeneratedValue
	private int id;
	private String address;
	private String service;
	private double basicFee;
	@ManyToOne
	private EstateAgency estateAgency;
	
	public Property(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getService(){
		return service;
	}
	public void setService(String service){
		this.service = service;
	}

	public double getBasicFee() {
		return basicFee;
	}

	public void setBasicFee(double basicFee) {
		this.basicFee = basicFee;
	}

	public EstateAgency getEstateAgency() {
		return estateAgency;
	}

	public void setEstateAgency(EstateAgency estateAgency) {
		this.estateAgency = estateAgency;
	}	
}
