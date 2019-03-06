package com.fja.spring.beanInital;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fja.spring.beanInital.model.Bird;
import com.fja.spring.beanInital.model.Cat;
import com.fja.spring.beanInital.model.Fish;

public class App {
	/**
	 * bean初始化执行的方法和销毁执行的方法
	 *  第一种：bean实现InitializingBean和DisposableBean接口（spring提供）
	 *  
	 *  第二种：通过注解（initMethod、destroyMethod）指定bean的初始化方法和销毁方法
	 *  
	 *  第三种：JSR250提供的注解@PostConstruct和@PreDestroy
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
		
		context.getBean(Cat.class);
		context.getBean(Bird.class);
		context.getBean(Fish.class);
		
		context.close();
	}
}

@Configuration
class BeanConfig {
	
	@Bean
	public Cat getCat() {
		return new Cat();
	}
	
	@Bean(initMethod="init", destroyMethod="invalidate")
	public Bird getBird() {
		return new Bird();
	}
	
	@Bean
	public Fish getFish() {
		return new Fish();
	}
}