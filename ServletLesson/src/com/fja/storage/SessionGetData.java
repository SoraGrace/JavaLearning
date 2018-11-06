package com.fja.storage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionGetData extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.获取session对象
		HttpSession session = req.getSession();
		
		//获取session的编号
		String id = session.getId();
		System.out.println("session的ID= "+id);
		
		//2.获取数据
		String user = (String)session.getAttribute("user");
		
		/**
		 * 现象：同一个浏览器，打开一个新的窗口还是可以取到数据，但是关闭浏览器在打开后无法取到session数据了
		 * 原因：
		 * 	1.第一次访问服务器，服务器创建一个session对象，并且给session对象分配一个唯一的ID，叫做JSESSIONID
		 * 	2.把JSESSIONID作为Cookie的值发送给浏览器保存，（响应头是Set-Cookie）
		 * 	3.下一次访问浏览器的时候，浏览器会在请求头中携带JSESSIONID的数据
		 * 	4.服务器得到请求头中的JSESSIONID，在服务器的内存（map）中搜索是否存在对应id（key）的session对象（value）。
		 * 	5-1.如果找到对应的id的session对象，则取出返回
		 * 	5-2.如果找不到对应id的session对象，则创建一个新的session对象，分配ID发送给浏览器（就是第一步的操作流程）
		 * 
		 * 而保存JSESSIONID的cookie的有效时间设置的是负数，也就是关闭浏览器后JSESSIONID就失效了，
		 * 因此重新打开一个新的窗口访问服务器时，是没有JSESSIONID的，所以服务器当然就不然找到对应的session对象，
		 * 也就取不到数据。session技术一定程度上还是依赖cookie技术的
		 */
		System.out.println(user);
	}
}
