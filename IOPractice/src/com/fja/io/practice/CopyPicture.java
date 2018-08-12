package com.fja.io.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 练习：用文件输入输出流拷贝图片 
 */
public class CopyPicture {

	public static void main(String[] args) {
		copy();
	}
	
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
}
