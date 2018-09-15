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
 * 		 3. 一个try代码块可以跟有多个catch块，也就是一个try代码块可以捕获多个异常。
 * 		 4. try代码块可以可以跟有多个catch块，但是捕获的异常类型必须从小到大，否则无法通过编译。
 * 	2.抛出处理
 * 	   【注意】
 * 		1. 如果一个方法的内部抛出了一个编译时异常对象，那么必须要在方法上声明抛出。
 * 		2. 如果调用了一个声明抛出编译时异常的方法，那么调用者必须要处理try/catch或者继续抛出处理。
 * 		3. 如果一个方法内部抛出了一个异常对象，那么throw语句后面的代码都不会执行了，方法停止。
 * 		4. 在一种情况下只能抛出一种类型的异常。
 * 
 * throw与throws关键字的区别：
 *  	1. throw关键字用于方法内部,throws关键字用于方法声明
 * 		2. throw关键字后面接的是异常对象，throws后面接的是抛出的异常类型
 * 		3. throw关键字后面只能接一个异常对象，throws关键字后面可以接多种类型的异常声明(用逗号分隔)。
 * 
 * 
 * 问：什么时候用抛出处理，什么时候用捕获处理
 * 答：如果需要通知到调用者代码出了问题，就使用抛出处理。不需要通知调用者，自己能处理的异常则使用捕获处理。
 */
public class ExceptionClass {

	public static void main(String[] args) {
		divide(10,0);
		
		//调用声明了异常的方法，就必须要对其进行处理
		try {
			traversal(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void divide(int a,int b){
		int c = 0;
		try{
			c = a/b;
			System.out.println("可以执行么？");//出现异常后不会执行
		}catch(ArithmeticException e){
			//e就指向jvm创建的对应的异常对象
			e.printStackTrace();
		}catch(Exception e){
			//捕获的异常类型必须从小到大，否则无法通过编译。
			e.printStackTrace();
		}
		System.out.println("c="+c);		//捕获处理之后程序会继续执行
	}
	
	//抛出处理
	public static void traversal(int[] arr) throws Exception{
		if(arr == null){
			throw new Exception();
		}
		//如果执行了throw语句，那么throw语句后面的代码都不会执行了
		for(int i : arr){
			System.out.println(i);
		}
	}
}
