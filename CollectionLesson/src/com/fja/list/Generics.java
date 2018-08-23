package com.fja.list;
/**
 * 泛型的好处：
 * 1、将运行时异常提前至了编译时
 * 2、避免了无谓的强制类型装换
 * 
 * 【注意】
 *  泛型是JDK1.5使用的新特性
 *  
 *  泛型的写法：
 *  ArrayList<String> list = new ArrayList<String>()   ----推荐使用
 *  ArrayList list = new ArrayList<String>()           ----兼容JDK1.5以前的代码
 *  ArrayList<String> list = new ArrayList()		   ----兼容JDK1.5以前的代码
 *  
 *  泛型：   左右都写，左不写右写，左写右不写，编译时看左边。
 *  泛型没有多态，左右两边泛型的数据类型必须一致，否则无法通过编译
 *  ArrayList<Object> list = new ArrayList<String>()  错误的写法
 *  
 *  
 *  自定义泛型：自定义泛型就是一个数据类型的占位符或者是一个数据类型的变量
 *  方法中的泛型：
 *   在方法中的自定义泛型，它的具体数据类型是在调用该方法的时候，传入实参时决定的，传入什么类型的数据，自定义泛型就代表什么类型的数据。
 *   自定义泛型只要符合标识符的命名规则即可，尖括号中声明的和占位符一致即可。一般使用T(type)或者E(element)来表示自定义泛型
 *   
 */
public class Generics {
	
	public static void main(String[] args) {
		String data = getData("String类型数据");	//返回值是String
		Integer _data = getData(1234567);		//返回值是Integer
		//这里有一点需要注意：在泛型中不能使用基本数据类型，需要使用基本数据类型的包装类型
	}
	
	//一. 在方法上自定义泛型,使用自定义泛型在返回值位占位，并且用<>声明
	public static <T>T getData(T t ){
		return t;
	}
	
	/**
	 * 在类上的声明的自定义泛型 
	 * 优点：只要声明一次，类包含的方法就不需要在声明自定义泛型了
	 */
}
