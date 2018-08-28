package com.fja.io.buffered;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class _Writer {
	/**
	 * ------|Writer 输出字符流的基类
	 * ---------|FileWriter 向文件输出字符数据的输出字符流
	 * ---------|BufferedWrtier 缓冲输出字符流，作用是提高了FileWriter的写数据的效率，最重要的是拓展了FileWriter的功能
	 */
	public static void main(String[] args) {
		baseProcess();
	}
	
	
	public static void baseProcess(){
		File file = new File("E:\\test.txt");
		BufferedWriter bw = null;
		try{
			//writer抽象类内部本身就有一个char[]数组，大小是1024
			FileWriter fw = new FileWriter(file);
			/**
			 * BufferedWriter内部的char[]数组是默认8192大小，
			 * 可以通过BufferedWriter的构造器指定char数组的大小
			 */
			bw = new BufferedWriter(fw,2048);
			//写数组
			bw.write("老当益壮,宁移白首之心");
			//这是BufferedWriter拓展的功能,换行。实际上就是向文件输出\r\n,相当于bw.write("\r\n");  
			bw.newLine();
			bw.write("穷且益坚,不坠青云之志");
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(bw!=null)bw.close();					//没有调用flush()或者close()只有当char[]数组填满时才会真正写到硬盘里。
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
