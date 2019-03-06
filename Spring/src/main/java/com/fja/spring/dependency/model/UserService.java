package com.fja.spring.dependency.model;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	//JSR250提供的Resource注解也可以进行依赖注入
	@Resource
	public UserDao userDao;

	@Override
	public String toString() {
		return super.toString()+"{"+userDao.toString()+"}";
	}
	
}
