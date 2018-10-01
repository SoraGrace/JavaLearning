package com.fja.web.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应头：content-type 
 * 		设置服务器发送给浏览器的数据类型，以及编码格式
 * 
 * 浏览器会以收到的content-type类型去解析
 */
public class ResponseContentType extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String html = "<html><head><title>content-type</title></head><boby>test content type!!!</body></html>";
		
		//设置响应头,方式一：
		//resp.setHeader("content-type","text/html");
		
		//方式二：和上面代码效果相同
		//resp.setContentType("text/html");
		
		//浏览器会以xml的方式去解析
		//resp.setContentType("text/xml");
		
		//resp.getWriter().write(html);
		
		
		/**
		 * 当然还可以发送图片/下载图片，以字节传输
		 */
		File file = new File("D:/alien.jpg");
		
		//1.首先设置响应头,告诉浏览器我要发送图片。至于传入参数需要怎么写，可以在%tomcat%/conf/web.xml的mine-mapping标签中查看
		resp.setContentType("image/jpg");
		
		//1.5设置Content-Disposition，告诉浏览器以下载的方式打开资源，如果不设置，仅仅是在浏览器中展示图片，并不会下载。
		resp.setHeader("Content-Disposition", "attachment;filename="+file.getName());
		
		//2.获取服务器字节的输出流
		ServletOutputStream outputStream = resp.getOutputStream();
		
		FileInputStream fis = new FileInputStream(file);
		
		byte[] buff = new byte[1024];
		
		int len = 0;
		//3.边读取边输出
		while((len = fis.read(buff)) != -1){
			outputStream.write(buff,0,len);
		}
		
		fis.close();
	}
}
