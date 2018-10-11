package com.fja.base;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * Servlet 接口   实现一个Serlvet接口就是一个Servlet类了，注意区分Serlvet和HttpServlet（平时我们指的是HttpServlet）
 * -----|GenericServlet 抽象类
 * -------------|HttpServlet 基于Http协议的servlet 有doGet doPost方法
 * 
 * Servlet的生命周期：
 *    1. Servlet程序的生命周期是由服务器(tomcat)控制的
 *    2. 构造方法、init()、service()、destroy()构成了servlet的生命周期
 *   
 *    
 * 默认的情况下，第一次访问servlet的时候创建servlet对象。如果某个Servlet的构造方法或者是init()方法中执行了较多的逻辑代码，
 * 那么会导致用户第一次访问这个servlet的时候花费较多的时间。因此我们可以改变Servlet创建对象的时机，在tomcat启动的时候就创建这个Servlet的对象
 * 只需要在web.xml中添加<load-on-startup>1</load-on-startup>即可。标签中间的数字表示优先级，数字越大优先级越低
 * 
 */
public class ServletLifecycle extends HttpServlet{
	
	//构造方法，创建servlet对象的时候调用。默认情况下，第一次访问一个servlet的时候创建servlet。
	//默认情况下一个servlet的构造函数只会调用一次，默认情况下servlet是单例的
	public ServletLifecycle(){
		System.out.println("servlet对象被创建");
	}
	
	//init方法，创建完servlet对象的时候调用（注意是创建完），默认情况下一个servlet也只会调用一次
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("调用init初始化");
	}
	
	//service方法，每次访问servlet时都会调用，根据请求的方式决定调用doGet()还是doPost()
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		super.service(req, res);
		System.out.println("service方法被调用");
	}
	
	//销毁，停止服务器或者是重新部署了web应用的时候都会销毁所有的servlet，在销毁servlet的时候就会调用destroy方法
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("servlet对象销毁了");
	}
}
