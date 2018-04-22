package com.jnPrestations.main;

import java.awt.Point;

import com.jnPrestations.enumerations.FrameModels;
import com.jnPrestations.factories.FrameFactory;

public class Main {

	public static Point point = new Point(100, 50);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FrameFactory.buildFrame(FrameModels.LandingFrame);
	}
}
