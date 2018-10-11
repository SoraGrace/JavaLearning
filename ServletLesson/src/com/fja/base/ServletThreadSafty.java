package com.fja.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet的线程安全 
 * 默认情况下，注意是默认情况下，一个Servlet的构造方法只会被执行一次，也就是说Servlet默认是单例的。
 * 而每当有一个请求的时候服务器都会创建一个线程，那么就会出现多个线程访问一个Servlet对象的情况，也就是说会产生线程安全问题。
 * 
 * 比如Servlet中存在成员变量，且有非原子性操作的语句，或者多个语句访问这个成员变量，就会产生线程安全问题。
 * 
 * ps.
 * 问：count++是线程安全的么？
 * 答：不是，因为count++不是原子性操作
 * 
 * 解决方案：
 * 	1. 使用同步代码块。因为会产生效率问题，所以在使用同步代码块的时候，要尽量缩小同步代码块的范围。
 * 	2. 使用原子性操作，且不能是多句语句。
 * 	3. Servlet中不使用共享资源，比如不设置成员变量，若果必须使用的话，参考上述1,2
 */
public class ServletThreadSafty extends HttpServlet{
	
	int count = 1;
	
	public ServletThreadSafty() {
		System.out.println("构造方法被执行了");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		resp.getWriter().write("您是网站的第"+count+"个访客");
		
		//模拟cpu处理性能不足
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		count++;
	}
}
