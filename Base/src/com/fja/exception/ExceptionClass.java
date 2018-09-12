package com.fja.exception;
/**
 * 运行期异常：
 * 	jvm一旦发现运行期异常，例如除数为零，就会创建对应的异常对象，并且会调用
 *  异常对象的printStackTrace()方法，并且程序不再执行。
 * 
 * 异常处理的方式：
 * 	1.捕获处理
 * 	    【注意】
 * 		 1. 如果try代码块中的代码出现异常经过catch代码块处理之后，try/catch代码块之后的代码可以继续执行。
 * 		 2. 如果try代码块中的代码出现异常，在try代码块中问题语句后面的代码都不会执行。
 * 	2.抛出处理
 */
public class ExceptionClass {

	public static void main(String[] args) {
		divide(10,0);
	}
	
	public static void divide(int a,int b){
		int c = 0;
		try{
			c = a/b;
			System.out.println("可以执行么？");//出现异常后不会执行
		}catch(ArithmeticException e){
			//e就指向jvm创建的对应的异常对象
			e.printStackTrace();
		}
		System.out.println("c="+c);		//捕获处理之后程序会继续执行
	}
}
