package com.jnPrestations.testCases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jnPrestations.beans.MyBusiness;
import com.jnPrestations.manages.ManageMyBusiness;

public class MyBusinessTest {
	
	ManageMyBusiness mmy = new ManageMyBusiness();
	MyBusiness myBusiness = mmy.find();
	
	@Test
	public void fetchMyBusinessLegaName(){
		
		String  expectedResult = "SC NETTOYAGE";	
		assertEquals(expectedResult, myBusiness.getLegalName());
	}
/*	
	@Test
	public void billNumberIncrementationETest(){
		int currentBillNumber = myBusiness.getBillNumber();
		mmy.incrementBillNumber();
		myBusiness = mmy.find();
		int incrementedBillNumber = myBusiness.getBillNumber();
		assertEquals(incrementedBillNumber, currentBillNumber + 1);
	}
*/
}
