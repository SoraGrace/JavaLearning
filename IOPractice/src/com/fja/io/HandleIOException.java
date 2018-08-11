package com.fja.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 处理IO异常 
 */
public class HandleIOException {
	public static void main(String[] args) {
		read();
	}
	
	public static void read(){
		FileInputStream fis = null;
		try{
			File file = new File("E:\\README.md");
			fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int length = 0;
			while((length = fis.read(buf))!=-1){
				System.out.println(new String(buf,0,length));
			}
		}catch(IOException e){
			/**
			 * 在处理IO异常的时候，首先要阻止后面的逻辑代码。
			 * 所以首先要抛一个异常，当遇到throw关键字的时候，后面的代码不执行，return也可以让后面的代码不执行，
			 * 但是return会让调用者无法知道发生了什么异常。此外，需要让用户知道有异常发生，并且不能强制让用户处理，
			 * 因此抛出已检查异常(在方法后面声明，会强制让调用者处理)是不行的。所以选择抛出运行中异常，
			 * 将IO异常作为传入参数，使用户知道发生了什么异常。这样既可以抛出已检查异常，又可以不强制调用者处理
			 */
			throw new RuntimeException(e);
		}finally{
			try {
				if(fis!=null)
					fis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}		
	}
}
