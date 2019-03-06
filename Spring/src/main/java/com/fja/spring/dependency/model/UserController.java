package com.fja.spring.dependency.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
	//依赖注入，@Autowired自动装配
	@Autowired
	public UserService userService;
	
	@Override
	public String toString() {
		return super.toString()+"{"+userService.toString()+"}";
	}
}
