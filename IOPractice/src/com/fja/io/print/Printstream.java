package com.fja.io.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 打印流(PrintStream)：
 * -----|OutputStream 
 * -----------|FilterOutputStream
 * ---------------------|PrintStream
 * 
 * 打印流可以打印任意类型的数据，打印数据之前，会把数据转换成字符串再进行打印
 */
public class Printstream {
	public static void main(String[] args) {
		baseProcess();
	}
	
	public static void baseProcess(){
		File file = new File("E:/WriteTest.txt");
		PrintStream ps = null;
		try {
			ps = new PrintStream(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		//可以打印int,打印前会转换成字符串
		ps.print(97);
		//可以打印小数
		ps.println(3.14);
		//可以打印字符
		ps.println('a');
		//可以打印布尔值
		ps.println(true);
		//对象也可以打印
		ps.println(new Cat("汤姆","grey",5));
	}
}


class Cat{
	String name;
	String color;
	int age;
	public Cat(String name, String color, int age) {
		super();
		this.name = name;
		this.color = color;
		this.age = age;
	}
}