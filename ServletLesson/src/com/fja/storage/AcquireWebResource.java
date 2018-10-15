package com.fja.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取web应用资源：
 * 	在应用中如何写正确的请求路径：
 * 		情况一     转发：         getServletContext().getRequestDispatcher("/xxx").forword(req,resp) 
 * 		情况二     重定向：     request.sendRedirect("/xxx")
 * 		情况三     超链接：     <a href="/xxx"></a>
 * 		情况四     表单提交： <form action="/xxx"></form>
 * 	问：上述四种情况的请求路径应该怎么写？
 * 	答： 只需要根据一个准则进行判断即可，请求的资源给服务器使用还是浏览器使用。
 * 	         给服务器使用： /  表示当前web应用的根目录（WebRoot）
 * 	         给浏览器使用：/  表示%服务器%/webapps目录
 * 	        
 * 		 根据上述的准则进行分析：
 * 			（假设请求的是WebRoot目录下的index.jsp资源）
 * 			情况一：转发是服务器内部的行为，资源是给服务器使用，/代表的是WebRoot目录，因此路径就是/index.jsp
 * 
 * 			情况二：重定向是浏览器再次发送请求，资源是是浏览器使用，/代表webapps目录，不清楚具体是哪一个web应用。
 * 				     因此需要指定web应用的路径，先进入web应用的目录。最后请求的路径就是/ServletLesson/index.jsp
 * 
 * 			情况三：超链接，资源是给浏览器使用，/代表webapps目录，因此还是不清楚具体是哪一个web应用，请求的路径应为/ServletLesson/index.jsp。
 * 
 * 			情况四：表单提交，资源是给浏览器使用，/代表的是webapps目录，因此理由同上，请求的路径应为/ServletLesson/index.jsp。
 */
public class AcquireWebResource extends HttpServlet{
	//需求，读取userInfo.properties的内容
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		Properties $props = new Properties();
		/**
		 * 问："./resource/userInfo.properties"这个路径对么？
		 * 答：如果是在eclipse或者其他工具运行的普通的java项目则是对的，但是web项目就是错的。这里需要明确   . 代表的目录究竟是什么。
		 * 	  . 代表的是java命令运行的目录。在eclipse等工具上面运行正确是因为，工具帮开发者做了两步操作，第一步跳转到当前项目的目录下，这就是为什么文件路径都是src开始的，
		 * 	     第二步，用set classpath=xxx 命令将目录定位到项目的bin目录下，确保能找到运行的类的字节码文件。在回到当前的项目，为什么web项目就找不到properties文件了呢？因为
		 * 	  web项目运行在服务器上时，. 代表的也是java命令运行的目录，这个目录就是%tomcat%/bin目录，所以properties文件当然是找不到的。
		 */
		
		//props.load(new FileInputStream(new File("./resource/userInfo.properties")));
		
		/**
		 * 问：那想要加载web项目的文件（例如userInfo.properties）需要如何操作？
		 * 答：getRealPath()或者getResourceAsStream()
		 * 	  【注意】服务器请求资源，因此/表示的是WebRoot，并且项目src的文件在部署到tomcat上的时候都会被复制到classes目录下。
		 */
		//方法一：
		String path = getServletContext().getRealPath("/WEB-INF/classes/com/fja/storage/resource/userInfo.properties");		//返回资源的绝对路径
		props.load(new FileInputStream(new File(path)));
		String username = props.getProperty("username");
		System.out.println(username);
		
		//方法二：
		InputStream inStream = getServletContext().getResourceAsStream("/WEB-INF/classes/com/fja/storage/resource/userInfo.properties");				//返回输入流
		$props.load(inStream);
		String $username = $props.getProperty("username");
		System.out.println($username);
	}
}
