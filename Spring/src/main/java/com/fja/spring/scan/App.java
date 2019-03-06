package com.fja.spring.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		//AnnotationConfigApplicationContext的构造函数除了传入.class，还能出入字符串，spring会去扫描包里有注解的bean
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.fja.spring.scan.model");
		System.out.println(context.getBean("student"));
		context.close();
	}
}
