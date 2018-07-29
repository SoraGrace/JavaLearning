package com.fja.test.model;

public class Benz implements ICar{
	private String brand;
	private double price;
	private String color;
	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("speed up "+color+"的"+brand+"!");
	}
	public Benz(String brand, double price, String color) {
		super();
		this.brand = brand;
		this.price = price;
		this.color = color;
	}

}
