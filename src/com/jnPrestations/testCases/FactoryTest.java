package com.jnPrestations.testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jnPrestations.enumerations.FrameModels;
import com.jnPrestations.factories.FrameFactory;

public class FactoryTest {
	
	@Test
	public void testFactory(){
		String className = new FrameFactory().buildFrame(FrameModels.PropertyInputFrame).getClass().toString();
		assertEquals(className,"class com.jnPrestations.frames.PropertyInputFrame");
	}
}
