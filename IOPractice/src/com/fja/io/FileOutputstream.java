package com.fja.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 输出字节流：
 * 	------|OutputStream(抽象类)：是所有字节输出流的超类
 *	-----------|FileOutputStream：文件输出字节流
 * 	
 * 
 */
public class FileOutputstream {
	public static void main(String[] args) {
		try {
			//baseProcess();
			//baseEnhance();
			//appendContent();
			writeTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void baseProcess() throws IOException{
		/**
		 * 文件字节输出流使用步骤 ：
		 * 1). 找到目标文件
		 * 2). 建立数据的输出通道
		 * 3). 调用write()方法写数据，write方法可以传入int类型的参数，但是因为char可以自动转换成int类型，因此write中也可以传入char类型
		 * 4). 释放资源
		 */
		File file = new File("E:\\README.md");
		FileOutputStream fos = new FileOutputStream(file);
		//write()方法和read()一样，只能一个字节一个字节的写入数据
		fos.write('h');
		fos.write('e');
		fos.write('l');
		fos.write('l');
		fos.write('o');
		fos.write(' ');
		fos.write('w');
		fos.write('o');
		fos.write('r');
		fos.write('l');
		fos.write('d');
		fos.close();
	}
	
	//通过字节数组存放需要写出的字节数据
	static void baseEnhance() throws IOException{
		File file = new File("E:\\README.md");
		//【细节】建立输出通道的时候，如果目标文件不存在的时候会自动创建目标文件。使用FileOutputStream写入数据的时候，如果目标文件应存在，会先清空目标文件中的数据。
		FileOutputStream fos = new FileOutputStream(file);
		String data = "hello world";
		//通过getBytes()方法将字符串变成字节数组，传入到write()方法中去
		fos.write(data.getBytes());
		fos.close();
	}
	
	//追加数据,不清空目标文件原有的内容,使用FileOutputStream(File file,boolean flag)构造函数
	static void appendContent() throws IOException{
		File file = new File("E:\\README.md");
		//如果第二个参数是true,则将字节写入文件的末尾，而不是写入文件的开始处
		FileOutputStream fos = new FileOutputStream(file,true);
		String data = "!!!!!";
		//通过getBytes()方法将字符串变成字节数组，传入到write()方法中去
		fos.write(data.getBytes());
		fos.close();
	}
	
	/**
	 * 【注意】在使用FileOutputStream的write方法的时候，虽然接收的是一个int类型(32位)的数据，
	 *      但是真正写出的只是一个字节(8位)的数据，只把低8位的二进制数据写出，其余的二十四位数据全部丢弃
	 * 过程：
	 * 	我们输出一个int类型的32位数据(00000000-00000000-00000001-11111111)，低八位是最后的8个1，该数据换算成十进制是511
	 * 	如果write输出的是低八位，那么文件中的数据应该是11111111，前面的最前面的1会被丢失，而111111在byte中会进行补位变成-1（符号位1变负号，取反加1）
	 */
	static void writeTest() throws IOException{
		File file = new File("E:\\WriteTest.txt");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(511);
		fos.close();
		
		//将刚才写入的数据进行读取
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[4];
		fis.read(buf);
		System.out.println(Arrays.toString(buf));//[-1, 0, 0, 0],证明除了低八位的数据，int其余的位数的数据丢失
		fis.close();
	}
}
