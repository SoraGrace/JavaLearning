package com.fja.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fja.spring.annotation.model.Gundam;
import com.fja.spring.annotation.model.Zaku;

/**
 * 通过注解的方式创建bean
 * 不用通过application.xml进行bean的配置,更加便捷
 */
public class App {
	
    public static void main( String[] args ) {
    	//构造方法可以传入一个配置类的class文件
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
    	//获取bean实列
    	//1. 通过class
    	System.out.println(context.getBean(Gundam.class));
    	
    	//2.1 通过名字(默认是配置类中返回相应的bean的方法名)
    	System.out.println(context.getBean("createGundam"));
    	
    	//【注意】通过1 和 2.1 可以发现，bean的创建默认是单例的
    	
    	//2.2 通过自定义的名字返回
    	System.out.println(context.getBean("zaku"));
    	System.out.println(context.getBean("zaku"));
    	context.close();
    }
}

/**
 * 配置类使用：
 * 1. 配置类需要使用@Configuration注解
 * 2. 添加一个方法返回要配置的bean的实列，方法需要使用@Bean注解
 * 注意：
 * 	不能同时有两个方法返回相同类型的bean
 *  bean的创建默认是单例的,多例可以通过@Scope("prototype")注解来实现
 */
@Configuration
class BeanConfig {
	@Bean
	public Gundam createGundam() {
		return new Gundam();
	}
	
	@Bean(name = "zaku")
	@Scope("prototype")
	public Zaku createZaku() {
		return new Zaku();
	} 
}
