package com.fja.test.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
	static Object targetCar;
	public static Object getInstance(Object $target){
		targetCar = $target;
		return Proxy.newProxyInstance(
					targetCar.getClass().getClassLoader(),
					targetCar.getClass().getInterfaces(), 
					new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							// TODO Auto-generated method stub
							System.out.println("invoke的第三个参数= "+args);
							method.invoke(targetCar, args);
							return null;
						}
					});
	}
}
