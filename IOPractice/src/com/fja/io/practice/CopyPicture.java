package com.fja.io.practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 练习：用文件输入输出流拷贝图片 
 */
public class CopyPicture {

	public static void main(String[] args) {
		//copy();
		bufferCopy();
	}
	
	//使用文件字节流拷贝图片
	static void copy(){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try{
			File origin = new File("E:\\java icon.jpg");
			File dest = new File("D:\\java icon.jpg");
			fis = new FileInputStream(origin);
			fos = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer))!=-1){					//边读边写
				//因为buffer不会清空，因此每次读了多少数据就要写多少数据，fos.write(buffer)会导致重复复制倒数第二次的一部分数据。
				fos.write(buffer,0,len);	
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			//【细节】释放资源的顺序，先开后关，后开先关。
			try {
				if(fos!=null)fos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}finally{
				//要让输入流和输出流释放资源的语句有机会执行，因此不能写在一个try/catch块中
				try {
					if(fis!=null)fis.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			
		}
	}
	
	
	//使用缓冲字节流拷贝图片
	static void bufferCopy(){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try{
			File origin = new File("E:\\java icon.jpg");
			File dest = new File("D:\\java icon.jpg");
			FileInputStream fis = new FileInputStream(origin);
			FileOutputStream fos = new FileOutputStream(dest);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			int content = 0;
			while(content!=-1){
				content = bis.read();
				if(content!=-1)bos.write(content);
			}
			bos.flush();//这里其实调不调用flush其实都一样，因为close()里面也会调用flush()
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
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
	}
}
