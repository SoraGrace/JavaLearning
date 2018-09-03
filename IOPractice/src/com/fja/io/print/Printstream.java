package com.fja.io.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fja.io.FileOutputstream;

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
		ps.println(new Cat("汤姆","grey",5));//返回toString的结果
		
		//默认的输出就是向控制台输出
		System.out.println("Hello World");
		
		//重新设置了输出流对象
		System.setOut(ps);
		//结果就输出到了E:/WriteTest.txt中去
		System.out.println("Hello World");
		
		//打印流的另外一个用处就是收集异常日志信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		File errorLog = new File("E:/"+date+".log");
		PrintStream logPrintStream = null;
		try {
			/**
			 * 问：为什么要将FileOutputStream传入PrintStream的构造器中
			 * 答：因为异常日志需要追加，不使用FileOutputStream无法追加内容
			 */
			logPrintStream = new PrintStream(new FileOutputStream(errorLog,true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try{
			double res = 4/0;
		}catch(Exception e){
			//将写好的PrintStream传入printStackTrace()方法中，让异常输出到想要的目录中去
			e.printStackTrace(logPrintStream);
		}
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
	@Override
	public String toString() {
		return "名字： "+this.name+"  颜色： "+this.color+"  年龄： "+this.age;
	}
	
	
}