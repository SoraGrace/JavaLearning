package com.fja.spring.factorybean;

import com.fja.spring.factorybean.model.Boat;
//造船厂
public class BoatFactory {
	
	public Boat create() {
		return new Boat();
	}
}
