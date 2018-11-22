package com.listener.request;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 监听器三大类：
 * 	|----- 1.生命周期监听器：域对象的创建/销毁
 * 		|----- 1.1 ServletRequestListener					监听request对象的创建或销毁
 * 	 	|----- 1.2 HttpSessionListener						监听session对象的创建或销毁
 * 	 	|----- 1.3 ServletContextListener					监听ServletContext对象的创建或销毁
 *  |----- 2. 属性监听器：监听对象属性变化
 *   	|----- 2.1 ServletRequestAttributeListener			监听request对象的属性变化
 *   	|----- 2.2 HttpSessionAttributeListener				监听session对象的属性变化
 *   	|----- 2.3 ServletContextAttributeListener			监听ServletContext对象的属性变化
 *  |----- 3. 其他监听器：监听session对象
 *  	|----- 3.1 HttpSessionBindingListener        		监听对象绑定到session上的事件
 *  	|----- 3.2 HttpSessionActivitionListener			监听session序列化以及反序列化的事件
 */
public class RequestListener implements ServletRequestListener{
	
	//request对象的销毁
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		//获取当前的request对象
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		//获取参数
		String name = req.getParameter("name");
		System.out.println(name);
		System.out.println("销毁request对象");
	}
	
	//request对象的创建
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		String name = sre.getServletRequest().getParameter("name");
		System.out.println(name);
		System.out.println("创建request对象");
	}

}
