package com.fja.exception;
/**
 * 自定义异常：
 *  步骤：
 *  自定义一个类继承Exception
 */
public class CustomException {

	public static void main(String[] args) {
		try {
			connect(null);
		} catch (NoIPException e) {
			e.printStackTrace();
		}
	}
	
	public static void connect(String serverIP) throws NoIPException {
		if(serverIP == null){
			throw new NoIPException("没有找到服务器IP");
		}
	}
}


class NoIPException extends Exception{
	
	public NoIPException(){}
	
	public NoIPException(String msg){
		//Exception类有三种构造方法，可以选择性调用
		super(msg);
	}
}