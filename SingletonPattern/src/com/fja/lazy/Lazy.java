package com.fja.lazy;
/**
 * 懒汉式：
 * 	 步骤：
 * 		1.私有化构造函数
 * 		2.声明本类的应用类型变量，但是不要创建对象
 * 		3.声明一个公共静态方法获取本类的对象，获取之前先判断是否已经创建了本类的对象，
 * 		     如果已经创建了就直接返回该对象，如果没有创建，则先创建本类的引用类型变量，然后返回。
 */
public class Lazy {

	public static void main(String[] args) {

	}

}


class Single{
	//第一步：
	private Single(){};
	
	//第二步：
	private static Single s;
	
	//第三步：
	public static Single getInstance(){
		if(s==null){
			s = new Single();
		}
		return s;
	}
}