package com.fja.spring.prior;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fja.spring.prior.model.User;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class,User.class);
		
		/**
		 * 当在Spring注解中存在两个相同类的配置（例子中为@Component的User类和BeanConfig中的），
		 * 且使用类型获取bean时会报错，expected single matching bean but found 2
		 * 
		 * 解决方法：
		 * 设置优先级
		 */
		//不报错
		//context.getBean("user");
		
		//
		System.out.println(context.getBean(User.class));
		
		context.close();
	}
}


@Configuration
class BeanConfig {
	@Bean
	//相同类型的配置有多个时，有@Primary的配置优先
	@Primary
	public User getUser() {
		System.out.println("=====调用=====");
		return new User();
	}
}