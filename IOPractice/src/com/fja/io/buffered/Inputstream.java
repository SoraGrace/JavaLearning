package com.fja.io.buffered;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *  在使用文件字节流数组进行IO操作的时候，我们会使用缓冲字节数组提高读写的效率。
 *  Sun也给我们提供了一个缓冲输入字节流。
 *  
 * ------|InputStream(抽象类)所有输入字节流的基类，超类
 *    ------|FileInputStream 读取文件数据的输入字节流
 *    ------|BufferedInputStream 缓冲输入字节流，主要用于提高读取文件数据的效率
 *    
 * 在BufferedInputStream类中维护着一个8kb的字节数组
 */
public class Inputstream {

	public static void main(String[] args) {
		read();
	}
	
	static void read(){
		/**
		 *  使用步骤：
		 *   1). 找到目标文件
		 *   2). 建立数据的输入通道
		 *   3). 建立缓冲输入字节流
		 *   4). 读取文件数据
		 */
		BufferedInputStream bis = null;
		try{
			File file = new File("src\\com\\fja\\io\\README.md");
			FileInputStream fis = new FileInputStream(file);
			/**
			 * 问:为什么创建BufferedInputStream对象的时候需要传递FileInputStream对象，
			 * 答：这里其实用到了装饰者设计模式， BufferedInputStream对象本身是没有读取文件的能力的，它需要借助FileInputStream的读取能力
			 */
			bis = new BufferedInputStream(fis);
			int content = 0;
			/**
			 * 问：BufferedInputStream的作用是为了提高文件读取的效率，但是 BufferedInputStream的read()方法每次只读取了一个字节，为什么效率还会高呢？
			 * 答：read()方法会填充8kb的字节到预设的字节缓冲数组，因此read()返回的字节都是从数组(内存)中读取，因此效率高。但是理论上还是自己创建的缓冲字节数组传入
			 * 	  FileInputStream效率高
			 */
			while((content = bis.read())!=-1){
				System.out.print((char)content);
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try {
				bis.close();
			} catch (IOException e) {
				throw new RuntimeException(e); 
			}
		}
	}
}
