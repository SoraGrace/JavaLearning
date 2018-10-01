package com.fja.web.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletResponse extends HttpServlet{
	
	/**
	 * 1). tomcat服务器把请求的信息封装到了HttpServletRequest对象，并且把响应的信息封装到HttpServletResponse对象 
	 * 
	 * 2). tomcat调用service方法，service方法中调用doGet/doPost方法，将HttpServletRequest对象和HttpServletResponse对象传入
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//3). 通过HttpServletResponse对象的设置改变响应信息
		/**
		 * 响应行：
		 * 	1). 改变状态码：setStatus(状态码)   仅仅改变状态码
		 * 	
		 *  2). sendError(状态码)		改变状态码，并且在实体内容中添加对应的错误页面(html)
		 */
		//resp.setStatus(404);
		
		//resp.sendError(404);
		
		/**
		 * 响应头：
		 * 	setHeader(响应头名称,值)
		 */
		//修改服务器信息
		resp.setHeader("Server", "JBoss");
		//修改响应内容长度
		//resp.setHeader("Content-Length","800");
		
		/**
		 *  设置实体内容两种方式：
		 *  	1. resp.getWriter()         打印流
		 *  	2. resp.getOutputStream()   字节输出流
		 */
		
		//字符内容
		resp.getWriter().write("hello world");
	}
	
	//4).当执行完doGet/doPost方法后，tomcat服务器把response对象转换成响应格式的内容，再发送给浏览器。
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//字节内容
		resp.getOutputStream().write("hello world".getBytes());
	}
}
