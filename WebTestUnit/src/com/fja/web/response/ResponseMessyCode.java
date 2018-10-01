package com.fja.web.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应乱码的原因：
 * 	tomcat服务器在使用PrintWriter对象进行输出的时候默认使用iso-8859-1进行编码 
 */
public class ResponseMessyCode extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解决响应乱码问题
		
		String html = "<html><head><title>content-type</title></head><boby>我热爱我的祖国!</body></html>";
		
		resp.setContentType("text/html");
		
		/**
		 * 设置响应的实体内容的编码。
		 * 【注意】
		 * 	这个方式仅仅是在resp.getWriter()也就是打印流的时候才有用。
		 * 	当使用getOutputStream()的时候，数据是什么编码，OutputStream输出就是什么编码，
		 * 	比如resp.getOutputStream("中文".getBytes());  getBytes()默认使用
		 * 	操作系统默认的码表进行编码，如果是中文版window默认就是gbk，OutputStream输出也是gbk。
		 */
		resp.setCharacterEncoding("utf-8");
		//resp.setContentType("text/html;charset=utf-8");这样写也可以解决中文乱码
		
		resp.getWriter().write(html);
	}
}
