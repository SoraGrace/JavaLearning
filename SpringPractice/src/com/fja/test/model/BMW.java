package com.fja.test.model;

public class BMW implements ICar{
	private String brand;
	private double price;
	private String color;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public void drive() {
		// TODO Auto-generated method stub
		System.out.println("speed up "+color+"の"+brand+"!");
	}
	
}
