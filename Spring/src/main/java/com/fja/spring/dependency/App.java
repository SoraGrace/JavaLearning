package com.fja.spring.dependency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fja.spring.dependency.model.UserController;
import com.fja.spring.dependency.model.UserDao;
import com.fja.spring.dependency.model.UserService;

public class App {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(UserController.class,UserService.class,UserDao.class);
		System.out.println(context.getBean("userController"));
		context.close();
	}
}