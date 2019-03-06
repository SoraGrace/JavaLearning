package com.fja.spring.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.fja.spring.scan.model.Teacher;

public class _App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanScan.class);
		System.out.println(context.getBean("student"));
		//因为排除了Teacher，因此会报错，上下文中找不到Teacher
		System.out.println(context.getBean("teacher"));
		context.close();
	}
}

/**
 * 第二种扫描的方式
 * 可以使用excludeFilter设置不扫描的类
 */
@ComponentScan(basePackages="com.fja.spring.scan.model"
,excludeFilters=@Filter(type=FilterType.ASSIGNABLE_TYPE,classes={Teacher.class}))
@Configuration
class BeanScan {
	
}