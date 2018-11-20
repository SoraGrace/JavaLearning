package com.filter.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseProcessServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("4.servlet执行业务逻辑");
		
		//重定向，看是否被过滤器拦截
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
