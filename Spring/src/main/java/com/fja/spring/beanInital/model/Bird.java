package com.fja.spring.beanInital.model;

public class Bird {
	public void init() {
		System.out.println("========bean初始化========");
	}
	public void invalidate() {
		System.out.println("========bean销毁========");
	}
}
