package com.fja.base;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletContext(Servlet上下文对象)：
 * 	作用：表示当前web的应用环境，用于封装web.xml的所有信息
 * 
 * 	常用方法：
 * 		getContextPath()						获取当前web应用的路径，多数用于sendRedirect
 * 
 * 		getInitParameter()						获取web应用的初始化参数
 * 		getInitParameterNames()					
 * 
 * 		getAttribute()							ServletContext域对象相关方法
 * 		setAttribute(String,Object)
 * 		removeAttribute(String)
 * 
 * 		getRequestDispatcher(String)			转发，注意区分与sendRedirect()的不同
 * 
 * 		getRealPath(String)						获得web应用的资源文件
 * 		getResourceAsStream(String)
 * 
 * 	与ServletConfig对象的区别：
 * 		ServletConfig对象封装的是一个Servlet的初始化配置信息，一个web应用可以存在多个ServletConfig对象
 * 		ServletContext对象封装的是整个web应用的配置信息，一个web应用只能有一个ServletContext对象，作用于全局
 * 
 * 	创建：当服务器(tomcat)加载web应用的时候，会将读取的web.xml内容封装到ServletContext对象中去
 * 
 * 	获取：可以从ServletConfig对象的getServletContext()的返回值中获取ServletContext的对象
 * 
 * 	ps.
 * 		GenericServlet中还提供了一个getServletContext()的方法，不过方法内部还是通过先取到ServletConfig对象，
 * 	再通过ServletConfig对象的getServletContext()方法获取ServletContext()对象。因此获取ServletContext对象
 * 	的方式只有一种，那就是通过ServletConfig对象获取，这里注意区分。两个方法同一个原理而已。
 */
public class Servletcontext extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//方法一：通过ServletConfig对象获得ServletContext对象
		ServletContext servletContext = getServletConfig().getServletContext();
		
		//方法二：通过GenericServlet的getServletContext()
		servletContext = getServletContext();
		
		//当前web应用的路径 = /项目名称
		String contextPath = servletContext.getContextPath();
		System.out.println(contextPath);
		//ps.修改部署到服务器的web应用路径的方法：右键properties-->deployment-->deployment assembly-->修改Web Context Root 
		
		//sendRedirect()会用到当前web应用的路径,比如
		//resp.sendRedirect(contextPath+"/index.jsp");
		
		
		/**
		 * web应用参数的配置在web.xml中使用<context-param></context-param>进行配置
		 *【细节】<context-param></context-param>推荐写在开头，不能和<servlet></servlet>混着写
		 */
		String databasePath = servletContext.getInitParameter("database");
		System.out.println(databasePath);
		
		//遍历
		Enumeration<String> keys = servletContext.getInitParameterNames();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			String value = servletContext.getInitParameter(key);
			System.out.println(key+" = "+value);	//database = 192.168.1.104   pagePath = /pages/
		}
		
		
		
		/**
		 * ServletContext域对象：主要是用于数据的保存，可以在不同的动态资源之间共享数据
		 * ps.在servlet中共享数据还可以通过sendRedirect()，把数据通过url进行传递(get方式)
		 */
		
		//保存数据:
		servletContext.setAttribute("name", "铁蛋");
		//获取数据：
		Object attribute = servletContext.getAttribute("name");
		System.out.println((String)attribute);
		//删除数据：
		servletContext.removeAttribute("name");
		
		
		
		/**
		 * 转发：
		 *  1.地址栏不会改变
		 *  2.不能转发到当前web应用之外的web应用中去。
		 *  3.forward方法的调用者与被调用者之间共享Request和Response（也就是说要使用request域对象进行数据共享，只能用转发）
		 *  
		 * 重定向：
		 * 	1.地址栏变成重定向的地址
		 * 	2.可以重定向到其他web应用，甚至是其他外部的域名
		 * 	3.sendRedirect方法由于两次浏览器服务器请求，所以有两个Request和Response
		 */
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp?name=狗娃");
		dispatcher.forward(req, resp);
	}
}
