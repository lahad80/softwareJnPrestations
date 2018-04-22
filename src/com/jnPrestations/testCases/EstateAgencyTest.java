package com.jnPrestations.testCases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.Property;
import com.jnPrestations.manages.ManageEstateAgency;

public class EstateAgencyTest {

	ManageEstateAgency mea = new ManageEstateAgency();
	
	@Test 
	public void handledPropertiesCount(){
		EstateAgency estateAg = mea.find(2);
		List<Property> list = mea.listHandledProperties(estateAg);
		System.out.println(estateAg.getLegalName());
		assertEquals(list.size(), 4);
	}
}
