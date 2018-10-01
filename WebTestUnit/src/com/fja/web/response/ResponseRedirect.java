package com.fja.web.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求重定向：相当于超链接跳转页面 
 * 方法：发送一个302的状态码+Location的响应头
 * 
 * 过程：
 * 	浏览器-->发送请求-->服务器-->返回302+Location-->浏览器-->再次请求(地址就是服务器返回的响应头中的Location的值)
 * 
 * 【注意】
 * 		重定向浏览器一共发送了两次请求，因此会有两个HttpServletRequest、HttpServletResponse对象
 */

public class ResponseRedirect extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//需求跳转到  /pages/formsubmit/index.html
		
		//1.1 设置状态码
		resp.setStatus(302);
		
		//1.2 设置响应头       Location后面可以接url和uri
		resp.setHeader("Location","/WebTestUnit/pages/formsubmit/index.html");
		
		//方法二：resp.sendRedirect();其实就是上面两句代码的简化版写法
		//resp.sendRedirect("/WebTestUnit/pages/formsubmit/index.html");
	}
}
