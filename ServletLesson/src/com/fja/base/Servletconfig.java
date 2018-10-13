package com.fja.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  ServletConfig对象的主要作用是加载Servlet的初始化配置参数。
 *  
 *  创建：ServletConfig实例的创建在Servlet创建之后，init(ServletConfig)调用之前。
 *  获取：ServletConfig的实例可以通过init(ServletConfig)方法获取到
 *  
 *  ServletConfig的常用方法
 *  	getInitParameter(参数名)				根据key获取value
 *  	getInitParameterNames()				获取所有的key（用来遍历）
 *  	getServletName()					获取Servlet的名称
 */
public class Servletconfig extends HttpServlet{
	//该成员变量直接可以继承GenericServlet的，不必要再去重复声明。
	//private ServletConfig config;
	
	/**
	 * 1). 服务器（tomcat）把在web.xml文件中配置的初始化数据封装进ServletConfig对象中
	 * 2). 服务器（tomcat）调用init(ServletConfig)方法传入ServletConfig对象
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		/**
		 * 其实下方的this.config = config，在GenericServlet的init(ServletConfig config)方法中已经帮开发者做了，
		 * HttpServlet已经有一个全局的成员变量ServletConfig config保存初始化配置，因此不需要用户再去设置了。通过getServletConfig直接获取ServletConfig对象。
		 * 因此，init(ServletConfig config)看源码就可以知道就是把封装好数据的ServletConfig赋值给Servlet的全局变量，开发者没必要去重写这个方法
		 */
		//this.config = config;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//不需要重复的去写，直接通过getServletConfig()获取父类中的成员变量private ServletConfig config
		//String path = config.getInitParameter("picPath");
		
		//获取在web.xml中配置的需要读取的数据
		String path = getServletConfig().getInitParameter("picPath");
		
		//遍历所有的初始化参数
		Enumeration<String> keys = getServletConfig().getInitParameterNames();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			String value = getServletConfig().getInitParameter(key);
			System.out.println(key+" = "+value);		// picPath = D:/alien.jpg    filePath = E:/poet.txt
		}
		
		//输出流
		ServletOutputStream outputStream = resp.getOutputStream();
		
		File file = new File(path);
		
		if(file.exists()){
			int content = 0;
			
			FileInputStream fis = new FileInputStream(file);
			
			while((content = fis.read())!=-1){
				outputStream.write(content);
			}
		}else{
			resp.sendError(404);
		}
	}
}
