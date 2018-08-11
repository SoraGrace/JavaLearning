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
		try {
			copy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void copy() throws IOException{
		File origin = new File("E:\\java icon.jpg");
		File dest = new File("D:\\java icon.jpg");
		FileInputStream fis = new FileInputStream(origin);
		FileOutputStream fos = new FileOutputStream(dest);
		byte[] buffer = new byte[1024];
		int len = 0;
		while(len!=-1){					//边读边写
			len = fis.read(buffer);
			fos.write(buffer);
		}
		fis.close();
		fos.close();
	}
}
