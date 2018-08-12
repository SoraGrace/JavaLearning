package com.fja.io.buffered;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 缓冲输出字节流：
 * ------|OutputStream(抽象类)所有输入字节流的基类，超类
 *    ------|FileOutputStream 读取文件数据的输入字节流
 *    ------|BufferedOutputStream 缓冲输入字节流，主要用于提高读取文件数据的效率 
 * 
 */
public class Outputstream {

	public static void main(String[] args) {
		write();
	}
	
	static void write(){
		BufferedOutputStream bos = null;
		try {
			File file = new File("E:\\bufferedOutputStream.txt");
			FileOutputStream fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			/**
			 *【注意】
			 *	 使用BufferedOutputStream写数据的时候，write()方法是先把数据写到它内部维护的字节数组中。
			 *	 如果要把数据真正的写到硬盘上需要调用flush()方法或者是close()方法。
			 *	ps. 当内部维护的字节数组已经填满数据的时候，也会自动将数据写入硬盘当中
			 */
			bos.write("Hello World".getBytes());
			
			//把缓冲数组中内部的数据写到硬盘当中
			bos.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				//在close()方法中，也会调用flush()方法，因此也可以将缓冲数组中的数据写到硬盘上
				if(bos!=null)bos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
