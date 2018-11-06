package com.fja.storage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = req.getSession();
		
		//获取session的编号（JSESSIONID），浏览器会将这个ID保存下来，下次进行有效路径的访问时放在请求头中
		String id = session.getId();
		System.out.println("session的ID= "+id);
		
		//2.存储数据
		session.setAttribute("user", "狗剩");
		
		/**
		 * session的细节
		 * 1).
		 * 	getSession()/getSession(true):
		 * 		如果有匹配的session对象则直接返回。没有匹配到JSESSIONID或者没有JSESSIONID,会自动创建一个session对象，并且返回。
		 * 	getSession(false)
		 * 		如果有匹配的session对象则直接返回。没有匹配到JSESSIONID或者没有JSESSIONID,返回null,不会自动创建一个session对象
		 * 
		 * 2).
		 * 	setMaxInactiveInterval,设置session的有效时间,session的默认有效时间是【30分钟】。
		 * 	setMaxInactiveInterval接受的参数是整形，单位是【秒】。存活时间从最后一次访问该session对象开始计算
		 * 
		 * 3).
		 * 	在全局配置session的有效时间（在web.xml中添加配置信息）
		 * 	<session-config>
		 * 		<session-timeout>1</session-timeout>
		 * 	</session-config>
		 * 注意：这里配置的时间的单位是【分钟】
		 */
		
		//解决浏览器关闭，重开后，保存JSESSIONID的cookie失效，开发者可以手动将JSESSIONID的保存到cookie中去
		Cookie jsessionid = new Cookie("JSESSIONID",id);
		//手动设置有效时间，这样关闭浏览器后JSESSIONID不会立刻失效
		jsessionid.setMaxAge(60);
		//保存到响应头中
		resp.addCookie(jsessionid);
	}
}
