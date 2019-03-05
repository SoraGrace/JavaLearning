package com.fja.spring.beanInital.model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Cat implements InitializingBean, DisposableBean{
	
	//在初始化bean的时候都会执行afterPropertiesSet方法
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("======初始化时执行afterPropertiesSet方法======");
	}
	
	//在销毁bean的时候都会执行destroy方法
	@Override
	public void destroy() throws Exception {
		System.out.println("======销毁时执行destroy方法======");
	}
}
