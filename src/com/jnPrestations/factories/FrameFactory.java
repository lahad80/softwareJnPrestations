package com.jnPrestations.factories;

import com.jnPrestations.enumerations.FrameModels;
import com.jnPrestations.frames.EstateAgenciesListFrame;
import com.jnPrestations.frames.EstateAgencyInputFrame;
import com.jnPrestations.frames.InformationsFrame;
import com.jnPrestations.frames.InvoiceInputFrame;
import com.jnPrestations.frames.InvoiceViewingFrame;
import com.jnPrestations.frames.LandingFrame;
import com.jnPrestations.frames.OutstandingInvoicesFrame;
import com.jnPrestations.frames.PropertiesListFrame;
import com.jnPrestations.frames.PropertyInputFrame;
import com.jnPrestations.frames.TemplateFrame;

public class FrameFactory {

	public static TemplateFrame buildFrame(FrameModels frameModel){
		TemplateFrame frame = null;
		switch(frameModel){
			case LandingFrame:
				frame = new LandingFrame();
				break;
			case InformationsFrame:
				frame = new InformationsFrame();
				break;
			case EstateAgencyInputFrame:
				frame = new EstateAgencyInputFrame();
				break;
			case EstateAgenciesListFrame:
				frame = new EstateAgenciesListFrame();
				break;
			case PropertyInputFrame:
				frame = new PropertyInputFrame();
				break;
			case PropertiesListFrame:
				frame = new PropertiesListFrame();
				break;
			case InvoiceInputFrame:
				frame = new InvoiceInputFrame();
				break;
			case outstandingInvoicesFrame:
				frame = new OutstandingInvoicesFrame();
				break;
			
			default:
				break;
		}
		
		return frame;
	}
}
