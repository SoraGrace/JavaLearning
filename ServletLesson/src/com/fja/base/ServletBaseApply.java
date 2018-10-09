package com.fja.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet容器(tomcat)找到并且调用Servlet的过程：
 * 	1.通过uri中的项目名称到%tomcat%/webapps/目录下寻找项目的文件夹目录
 * 	2.(找到项目的目录后就需要找到具体调用的是哪一个Servlet)
 * 	    通过资源名称去web.xml配置文件中匹配对应的url-pattern。
 * 	3.找到对应的url-pattern后找到url-pattern对应的servlet-name。
 * 	4.找到对应的servlet-name后，通过servlet-name找到对应的servlet-class，也就是具体调用的servlet的字节码文件 
 * 	5.通过字节码文件和反射构建调用的Servlet的实例
 * 	6.最后调用相应的方法。
 * 
 * Servlet的映射：
 * 	精确匹配：资源名称和web.xml中url-pattern标签中的内容完全一致
 * 	模糊匹配：url-pattern使用通配符(/ 或者  /* 或者 /xxx/*  或者  *.后缀名)
 * 		   /等效于/*，这些是tomcat的缺省路径，不推荐使用
 * 
 * 【注意】
 * 	1. url-pattern只能以"/"或者"*"开头
 * 	2. /*和*.后缀名两种模糊匹配不能同时使用，例如 /base/*.do这种写法会在tomcat启动时报错
 * 	3. 当精确匹配和模糊匹配都能匹配到资源名称的时候，精确匹配优先级高	
 * 	4. /*和/xxx/*和/xxx/yyy/*都能匹配的时候，越精确的优先级越高
 * 	5. /*和*.后缀名两种模糊匹配都能匹配到资源名称的时候，/*优先级高
 * 
 * 
 * 三大原则：
 *	1. 精确路径匹配。例子：比如servletA 的url-pattern为 /test，servletB的url-pattern为 /* ，这个时候，如果我访问的url为http://localhost/test ，这个时候容器就会先进行精确路径匹配，发现/test正好被servletA精确匹配，那么就去调用servletA，也不会去理会其他的servlet了。 
 *
 *  2. 最长路径匹配。例子：servletA的url-pattern为/test/*，而servletB的url-pattern为/test/a/*，此时访问http://localhost/test/a时，容器会选择路径最长的servlet来匹配，也就是这里的servletB。 
 *
 * 	3. 扩展匹配，如果url最后一段包含扩展，容器将会根据扩展选择合适的servlet。例子：servletA的url-pattern：*.action
 * 
 */
public class ServletBaseApply extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("this is Servlet Base Apply");
	}
}
