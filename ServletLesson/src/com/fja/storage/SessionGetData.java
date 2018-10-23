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
		
		//2.获取数据
		String user = (String)session.getAttribute("user");
		
		/**
		 * 现象：同一个浏览器，打开一个新的窗口还是可以取到数据，但是关闭浏览器在打开后无法取到session数据了
		 * 原因：
		 */
		System.out.println(user);
	}
}
