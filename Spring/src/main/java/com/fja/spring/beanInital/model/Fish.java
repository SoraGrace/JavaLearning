package com.fja.spring.beanInital.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Fish {
	@PostConstruct
	public void initial() {
		System.out.println("=====JSR250提供的注解@PostConstruct=====");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("=====JSR250提供的注解@PreDestroy=====");
	}
}
