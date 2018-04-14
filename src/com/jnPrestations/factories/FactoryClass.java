package com.jnPrestations.factories;

import com.jnPrestations.frames.EstateAgenciesListFrame;
import com.jnPrestations.frames.EstateAgencyInputFrame;
import com.jnPrestations.frames.InformationsFrame;
import com.jnPrestations.frames.InvoiceInputFrame;
import com.jnPrestations.frames.LandingFrame;
import com.jnPrestations.frames.PropertiesListFrame;
import com.jnPrestations.frames.PropertyInputFrame;
import com.jnPrestations.frames.TemplateFrame;
import com.jnPrestations.frames.outstandingInvoicesFrame;
import com.jnPrestations.manages.ManageEstateAgency;
import com.jnPrestations.manages.ManageInvoice;
import com.jnPrestations.manages.ManageMyBusiness;
import com.jnPrestations.manages.ManageProperty;
import com.jnPrestations.beans.EstateAgency;
import com.jnPrestations.beans.Invoice;
import com.jnPrestations.beans.MyBusiness;
import com.jnPrestations.beans.Property;


public class FactoryClass {

	public Object createClass(String classType){
		// beans
		if(classType.equalsIgnoreCase("EstateAgency")){
			return new EstateAgency();
		}
		if (classType.equalsIgnoreCase("Property")){
			return new Property();
		}
		if (classType.equalsIgnoreCase("Invoice")){
			return new Invoice();
		}
		if(classType.equalsIgnoreCase("MyBussiness")){
			return new MyBusiness();
		}
		
		//Dao implementations
		if(classType.equalsIgnoreCase("ManageEstateAgency")){
			return new ManageEstateAgency();
		}
		if (classType.equalsIgnoreCase("ManageInvoice")){
			return new ManageInvoice();
		}
		if (classType.equalsIgnoreCase("ManageProperty")){
			return new ManageProperty();
		}
		if(classType.equalsIgnoreCase("ManageMyBusiness")){
			return new ManageMyBusiness();
		}
		
		//frames
		if(classType.equalsIgnoreCase("TemplateFrame")){
			return new TemplateFrame();
		}
		if (classType.equalsIgnoreCase("LandingFrame")){
			return new LandingFrame();
		}
		if (classType.equalsIgnoreCase("InformationsFrame")){
			return new InformationsFrame();
		}
		if(classType.equalsIgnoreCase("EstateAgencyInputFrame")){
			return new EstateAgencyInputFrame();
		}
		if(classType.equalsIgnoreCase("EstateAgenciesListFrame")){
			return new EstateAgenciesListFrame();
		}
		if (classType.equalsIgnoreCase("PropertyInputFrame")){
			return new PropertyInputFrame();
		}
		if (classType.equalsIgnoreCase("PropertiesListFrame")){
			return new PropertiesListFrame();
		}
		if(classType.equalsIgnoreCase("InvoiceInputFrame")){
			return new InvoiceInputFrame();
		}
		if (classType.equalsIgnoreCase("outstandingInvoicesFrame")){
			return new outstandingInvoicesFrame();
		}
			
		
		return null;
	}
}
