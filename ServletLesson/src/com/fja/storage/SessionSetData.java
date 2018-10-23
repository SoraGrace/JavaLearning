package com.fja.storage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie的局限：
 * 	1.Cookie的数据只有字符串类型
 * 	2.Cookie的数据不支持中文字符串，只能保存英文数据
 * 	3.Cookie大小限制是4KB，每个站点最多存放20个Cookie数据
 * 	4.Cookie可以被用户清除 
 * 
 * 由于Cookie的局限，开发者可以选择使用Session保存数据
 * 
 * Session的特点: 会话数据保存在服务器（内存中）
 * 
 * HttpSession的常用方法：
 * 	获取session对象：
 * 		HttpServlet.getSession()
 * 		HttpServlet.getSession(boolean create)
 * 	
 * 	设置对象：
 * 		setMaxInactiveInterval(int interval)		设置session的有效时间
 * 	
 * 	销毁：
 * 		invalidate()								销毁session对象
 * 	
 * 	保存数据：
 * 		setAttribute(String key,String value)		域对象的方法	
 * 
 * 	获取数据：
 * 		getId()										获取编号
 * 		getAttribute(String key)
 * 	
 * 	清除数据：
 * 		removeAttribute(String key)										
 */
public class SessionSetData extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.获取session对象
		req.getSession();
	}
}
