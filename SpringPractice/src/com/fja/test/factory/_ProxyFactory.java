package com.fja.test.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class _ProxyFactory {
	private Object targetCar;
	public _ProxyFactory(Object $target){
		this.targetCar = $target;
	}
	public Object getInstance(){
		return Proxy.newProxyInstance(
				targetCar.getClass().getClassLoader(),
				targetCar.getClass().getInterfaces(), 
				new InvocationHandler(){

					@Override
					public Object invoke(Object proxy, Method method, Object[] arg2)
							throws Throwable {
						// TODO Auto-generated method stub
						method.invoke(targetCar, arg2);
						return null;
					}
				});
	}
}
