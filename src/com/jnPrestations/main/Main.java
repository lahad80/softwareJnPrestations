package com.jnPrestations.main;

import java.awt.Point;

import com.jnPrestations.factories.FactoryClass;

public class Main {

	public static Point point = new Point(100, 50);;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FactoryClass fc = new FactoryClass();
		fc.createClass("LandingFrame");
		
	}
}
