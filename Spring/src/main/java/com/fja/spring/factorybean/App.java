package com.fja.spring.factorybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fja.spring.factorybean.model.Boat;
import com.fja.spring.factorybean.model.Car;

public class App {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		//根据类型获取
		System.out.println(context.getBean(Car.class));
		
		/**
		 * 根据名字获取
		 * 【注意】这里获取的不是工厂（CarFactory）的类而是工厂的产品，FactoryBean<T>中的T
		 */
		System.out.println(context.getBean("CreateCarFactory"));
		
		/**
		 * 问：如何获取工厂的实例，而不是工厂的产品？
		 * 答：根据类型以及&名字
		 */
		System.out.println(context.getBean(CarFactory.class));
		
		System.out.println(context.getBean("&CreateCarFactory"));
		
		//第二种工厂
		//通过类型获取
		System.out.println(context.getBean(Boat.class));
		//通过名字获取
		System.out.println(context.getBean("CreateBoat"));
		context.close();
	}
}


@Configuration
class BeanConfig {
	
	@Bean
	public CarFactory CreateCarFactory() {
		//这里返回的是工厂对象，而不是我们需要创建的对象
		return new CarFactory();
	}
	
	/**
	 * 第二种工厂
	 * 第一步，现在配置类中装配工厂（BoatFactory）
	 * 第二部，装配需要创建的产品对象（Boat），将工厂的对象传入，调用工厂的生产方法
	 */
	@Bean 
	public BoatFactory CreateBoatFactory() {
		return new BoatFactory();
	}
	
	@Bean
	public Boat CreateBoat(BoatFactory factory) {
		return factory.create();
	}
}