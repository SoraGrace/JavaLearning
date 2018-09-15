package com.fja.exception;
/**
 * ------|Throwable
 * -----------|Error
 * -----------|Exception
 * ----------------|编译时异常： 
 * 	如果一个方法内部抛出了一个编译时异常对象，那么方法上必须要声明，而且调用者也必须要处理。
 * ----------------|运行时异常：
 * 	如果一个方法内部抛出了一个运行时异常对象，那么方法上可以声明也可以不声明。调用者可以处理也可以不处理。
 * 
 * 
 * 运行时异常：
 * 	RuntimeException以及RuntimeException的子类
 * 	常见的运行时异常：
 * 		ArithmeticException
 * 		NullPointerException
 * 		IndexOutOfBoundsException
 * 编译时异常：
 * 	除了运行时异常其他都是编译时异常
 * 
 * 问：为什么编译时异常一定要处理，而运行时异常可处理可不处理
 * 答：运行时异常可以通过良好的编程习惯去避免的，所以jvm就不会强制要求处理运行时异常。
 * 	     编译时异常很多情况下是通过代码无法避免，比如IOException
 */
public class RunTimeException {

	public static void main(String[] args) {
		divide(6,0,null);
	}
	
	public static void divide(int a,int b,int[] arr){
		//可以通过代码规避ArithmeticException
		if(b==0){			
			return;
		}
		int c = a/b;
		System.out.println("c=" + c);
		
		
		//可以通过代码规避NullPointerException
		if(arr!=null){
			System.out.println(arr.length);
		}
		
	}
}
