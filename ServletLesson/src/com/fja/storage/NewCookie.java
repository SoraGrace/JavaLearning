package com.fja.storage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 什么是会话：
 * 打开浏览器-->访问了服务器内容-->关闭浏览器    的过程就是一次会话
 * 
 * 问：什么是会话管理
 * 答：在会话的过程中，会产生一些数据，开发者需要将这些会话数据保存下来。管理浏览器和服务器会话过程中产生的数据就是会话的管理。
 * 
 * 会话管理的技术：
 * 	客户端：Cookie，会话数据保存在浏览器客户端
 * 	服务器：HttpSession，会话数据保存在服务器端
 * 	
 * 
 * 	Cookie技术的过程：
 * 		1.服务器创建Cookie对象，把会话的数据存储到cookie对象中。
 * 		2.服务器发送Cookie信息到浏览器（通过响应头发送，响应头的key：Set-Cookie）。
 * 		3.浏览器得到cookie，然后保存在浏览器端
 * 		4.浏览器在下次访问服务器时，会带着cookie信息（通过请求头，请求头的key：Cookie）
 * 		5.服务器接收到浏览器带来的cookie的数据		
 * 
 * 	Cookie类的常用方法：
 * 	创建：		
 * 		Cookie(String key, String value)	构造器
 * 		
 * 	设置：
 * 		setPath(String uri)					设置Cookie的有效的访问路径
 * 		setMaxAge(int expiry)				设置Cookie的有效时间
 * 		setValue(String value)				设置Cookie的值
 * 	获取：
 * 		getName()							获取key
 * 		getValue()							获取value
 * 
 * 	Cookie相关方法：
 * 	发送：
 * 		response.addCookie(Cookie cookie)	注意这个方法是HttpServletResponse类的
 * 	接收：
 * 		request.getCookies()				服务器接收cookie，返回值是Cookie[]
 */
public class NewCookie extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.创建Cookie的对象
		Cookie name = new Cookie("name","tiedan");
		Cookie age = new Cookie("age","20");
		//2.发送Cookie到浏览器端（通过响应头发送：Set-Cookie）
		
		//原理：
		//resp.setHeader("Set-Cookie",name.getName()+"="+name.getValue()+","+age.getName()+"="+age.getValue());
		
		//resp封装的方法,效果和上方的方法相同，但是在有多个cookie对象需要发送到浏览器的时候，省略了开发者手动拼接数据的代码
		resp.addCookie(name);
		resp.addCookie(age);
		
		
		/**
		 * 浏览器在接收到响应头中的cookie信息后，会将数据保存在浏览器里，下次再请求该服务器的时候，会将cookie信息放在请求头中发给服务器 
		 * 服务器则可以通过getCookies()接收这些浏览器传来的Cookie
		 */
		//原理：
		String value = req.getHeader("cookie");
		System.out.println(value);				//name=tiedan; age=20
		
		//简化：
		Cookie[] cookies = req.getCookies();
		if(cookies == null){
			System.out.println("没有cookie数据");
			return;
		}
		for(Cookie cookie : cookies){
			System.out.println(cookie.getName()+"="+cookie.getValue());
		}
	}
}
