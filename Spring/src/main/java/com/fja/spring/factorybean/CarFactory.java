package com.fja.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

import com.fja.spring.factorybean.model.Car;

/**
 * FactoryBean
 * 		
 */
public class CarFactory implements FactoryBean<Car>{
	/**
	 * 获取FactoryBean实现类创建的对象
	 */
	@Override
	public Car getObject() throws Exception {
		return new Car();
	}

	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}
	
	/**
	 * 创建的对象是否是单例，默认是true(单例)
	 * 注意这个方法的修饰是default（非抽象方法），不需要实现类实现。
	 * ps.1.8中接口可以有静态方法，也不需要实现
	 */
	@Override
	public boolean isSingleton() {
		return FactoryBean.super.isSingleton();
	}
}
