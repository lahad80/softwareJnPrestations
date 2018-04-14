package com.jnPrestations.beans;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Invoices")
public class Invoice {
	
	@Id @GeneratedValue
	private int id;
	private int invNumber;
	private String dateOfIssue;
	private String period;
	private String status;
	@ManyToOne
	private Property property;
	
	public Invoice(){}

	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getInvNumber() {
		return invNumber;
	}

	public void setInvNumber(int invNumber) {
		this.invNumber = invNumber;
	}

	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getStatus (){
		return status;
	}
	
	public void setStatus (String status){
		this.status = status;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
}
