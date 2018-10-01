package com.fja.web.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定时刷新：
 * 	方法：  发送refresh的响应头
 *  原理：  浏览器获得refresh响应头之后，等待秒数重新请求，服务器又再次发送refresh响应头给浏览器，不断循环从而达到定时刷新的目的。
 */

public class ResponseRefresh extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.setHeader("refresh",秒数)
		//resp.setHeader("refresh","2");		//每隔两秒刷新一次页面
		
		//隔几秒跳转到某个页面 resp.setHeader("refresh",秒数;url=需要跳转的uri)
		resp.setHeader("refresh","3;url=/WebTestUnit/pages/formsubmit/index.html");
	}
}
