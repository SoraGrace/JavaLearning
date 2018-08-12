package com.fja.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 输入流：
 * 	字节流:字节流读取的都是文件中的二进制数据，读取的二进制数据不会进行任何的处理
 * 
 *  字符流:字符流也是读取文件中的二进制数据，不过会把这些二进制数据转化为能识别的字符，字符流=字节流+解码
 *  
 * ------|InputStream(抽象类)所有输入字节流的基类，超类
 *    ------|FileInputStream 读取文件数据的输入字节流
 */
public class FileInputstream {

	public static void main(String[] args) {
		try {
			arrayBufferEnhance();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//使用文件字节输入流的基本用法
	static void baseProcess() throws IOException{
		//基本使用步骤一：找到文件并用File类进行描述
		File file = new File("src\\com\\fja\\io\\README.md");
		//基本使用步骤二：建立数据传输的管道,会抛出异常，毕竟用户用File类描述的文件对象有可能找不到。
		FileInputStream fis = new FileInputStream(file);
		//基本使用步骤三：开始读取。【注意】read()方法只会读取【一个字节】的数据并且返回，返回的是int类型的数据。
		int contnt = fis.read();
		//基本使用步骤四：将通道关闭，释放资源，如果没有释放资源，其他程序不能对这个文件进行操作
		fis.close();
		
		System.out.println(contnt);		//230
		
		//总结：基本使用可以让我们读取到数据，但是由于read()方法只能读取到一个字节的数据，因此文件的数据不能被完整的读取，并且返回的数据因为没有解码因此不可读。
	}
	
	//文件字节流输入流的基本用法增强版，可以完整的读取数据
	static void baseEnhance()throws IOException{
		File file = new File("src\\com\\fja\\io\\README.md");
		FileInputStream fis = new FileInputStream(file);
		int contnt = 0;
		//因为知道read()方法在读取完文件的所有数据之后会返回-1，因此我们可以使用while循环来解决数据读取不完整的问题
		while((contnt = fis.read())!=-1){
			System.out.print((char)contnt);
		}
		fis.close();
		
		//虽然可以读取完整的数据，但是效率太低了，只能一个字节一个字节的读取，就好比去超市不推购物车，拿着商品就往收银台跑
	}
	
	//缓冲字节输入流
	static void arraybuffer() throws IOException{
		File file = new File("src\\com\\fja\\io\\README.md");
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];			//建一个缓冲字节数组，数据都会被写入这个字节数组中
		//将数组传入read()方法中，数据会被读取到数组中，返回值是读取了多少个字节的数据。对比与上面的方法，相当于推着购物车就超市购物
		int length = fis.read(buffer);			//返回值：本次read()方法读取了多少字节数据到字节数组中
		System.out.println(length);				//1024,读取了1024个字节
		fis.close();
		//在String的构造器中传入字节数组，会使用平台默认的字符集解码，生成新的字符串
		System.out.println(new String(buffer)); 
		
		//总结：虽然效率提高了，且可以解码成可都的内容，但是依旧不能完整的读取整个文件的数据
	}
	
	//缓冲字节流数组增强版,配合循环读取所有的数据
	static void arrayBufferEnhance() throws IOException{
		File file = new File("src\\com\\fja\\io\\README.md");
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];		//缓冲区的大小最好是1024或者1024的倍数，理论上来说缓冲数组越大读取的效率越高
		int len = 0;
		while((len = fis.read(buffer))!=-1){
			//【注意】从第0位开始到本次read()读取到的字节数据量为止，生成字符串。
			System.out.println(new String(buffer,0,len)); 	
			//为什么要这么做，原因是buffer的内容每次都是覆盖的，也就是说，最后一次的数据如果没有刚好装满数组,则前一次的数据会遗留下来,也就是数据会重复。
			//System.out.println(new String(buffer)); 
		}
		fis.close();
	}	
}
