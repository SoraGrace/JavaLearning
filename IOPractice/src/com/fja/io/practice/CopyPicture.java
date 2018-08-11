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
		while((len = fis.read(buffer))!=-1){					//边读边写
			//因为buffer不会清空，因此每次读了多少数据就要写多少数据，fos.write(buffer)会导致重复复制倒数第二次的一部分数据。
			fos.write(buffer,0,len);	
		}
		//【细节】释放资源的顺序，先开后关，后开先关
		fos.close();
		fis.close();
	}
}
