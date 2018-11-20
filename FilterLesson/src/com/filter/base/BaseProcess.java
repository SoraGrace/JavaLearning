package com.filter.base;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤器是Servlet技术的一种。
 * 
 * 过滤器执行流程：
 * 	1.用户访问服务器
 * 	2.过滤器对servlet请求进行拦截
 * 	3.请求进入过滤器，过滤器处理
 * 	4.经过处理过滤器放行，请求到达serlvet/jsp
 * 	5.Servlet执行逻辑
 * 	6.Servlet执行完，响应来到过滤器
 *  7.服务器将响应的内容返回给浏览器
 *  
 *  |---- javax.serlvet.Filter    公共接口
 *  
 *  过滤器需要实现的方法：
 *  	1. destory()
 *  	2. doFilter(ServletRequest,ServletResponse,FilterChain)
 *  	3. init(FilterConfig)
 *  
 *  Filter使用步骤：
 *  	1.实现Filter接口
 *  	2.配置过滤器类
 *  
 *  Filter的生命周期：
 *  	1.在服务器启动时创建过滤器实例和初始化过滤器
 *  	2.访问对应servlet时执行过滤器的doFilter方法chain.doFilter前的代码
 *  	3.过滤器执行chain.doFilter()方法放行，如果有下一个过滤器则进入下一个过滤器，否则进入servlet
 *  	4.servlet处理完成后再次执行doFilter方法chain.doFilter后的代码
 *  	5.关闭服务器时，销毁过滤器的实例
 */
public class BaseProcess implements Filter{
	
	public BaseProcess(){
		System.out.println("1.创建过滤器实例");
	}
	
	@Override
	public void destroy() {
		System.out.println("6.销毁过滤器实例");
	}
	
	//在请求到达servlet之前先进入此方法处理公用的业务逻辑操作
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("3.执行过滤器业务处理逻辑");
		
		//放行，如果有下一个过滤器则进入下一个过滤器，否则执行访问的servlet的逻辑代码
		chain.doFilter(request, response);
		
		
		System.out.println("5.Servlet处理完成，回到过滤器");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("2.执行过滤器初始化方法");
		/**
		 * filterConfig的相关方法：
		 * 1.getFilterName()			获取web.xml文件中<filter-name />的数据
		 * 2.getInitParameter()			获取初始化数据
		 * 3.getInitParameterNames()	获取所有初始化数据的键
		 * 4.getServletContext()		获取servletContext，和servlet一样
		 */
		String filterName = filterConfig.getFilterName();
		System.out.println("过滤器名称： "+filterName);
		
		Enumeration<String> keys = filterConfig.getInitParameterNames();
		//遍历
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			String value = filterConfig.getInitParameter(key);
			System.out.println(key+"\t"+value);
		}
	}
}
