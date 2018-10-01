package com.fja.web.request;

import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取请求行、请求头信息
 * 主要API：
 * 	请求行：
 * 		request.getMethod()				请求方式
 * 		request.getRequestURI()			请求的统一资源标记符
 * 		request.getProtocol()			请求的http协议
 * 	
 *  请求头：
 *  	request.getHeader(请求头名称)		根据请求头获取请求值
 *  	request.getHeaderNames()	          获取所有的请求头名称
 * 
 */
public class RequestHeaderServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		//请求方式
		String method = req.getMethod();
		System.out.println("请求方式: " + method);
		
		//uri
		String uri = req.getRequestURI();
		System.out.println("统一资源标记符： "+uri);
		
		//协议：
		String protocol = req.getProtocol();
		System.out.println("协议以及版本号： "+protocol);
		
		//遍历请求头：
		Enumeration<String> headers = req.getHeaderNames();
		
		while(headers.hasMoreElements()){
			String header = headers.nextElement();
			String value = req.getHeader(header);
			System.out.println(header + "=" + value);
		}
	}
}
