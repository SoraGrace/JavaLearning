package com.fja.storage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInvalidate extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//不创建，只获取
		HttpSession session = req.getSession(false);
		
		//做判断,目前会话存在session则删除
		if(session != null){
			session.invalidate();
			
			System.out.println("session对象删除成功");
		}
	}
}
