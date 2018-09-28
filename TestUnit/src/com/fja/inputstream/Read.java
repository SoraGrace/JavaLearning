package com.fja.inputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class Read {
	@Test
	public void read() throws IOException{
		/**
		 * 当记事本选择ANSI(gbk)编码保存时，需要每次获取2个byte，并且new String时的解码格式需要指定为gbk
		 * 如果文中还有英文会出现乱码，因为每次取2个字节，而英文只占一个字节
		 */
		FileInputStream fis = new FileInputStream(new File("D:/readChinese.txt"));
		byte[] buf = new byte[3];
		int len = 0;
		while((len=fis.read(buf))!=-1){
			System.out.print(new String(buf,0,len,"utf-8"));
		}
	}
}
