package com.listener.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session对象被创建了");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session对象被销毁了");
	}

}
